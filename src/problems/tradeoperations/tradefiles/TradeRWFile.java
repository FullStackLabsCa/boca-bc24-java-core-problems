package problems.tradeoperations.tradefiles;


import problems.tradeoperations.exception_files.HitErrorsThresholdException;
import problems.tradeoperations.tradinginterface.TradingInterface;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TradeRWFile implements TradingInterface {

    private final Connection connection;

    // Constructor to accept Connection
    public TradeRWFile(Connection connection) {
        this.connection = connection;
    }

    public static void readFileStatic(String filePath, Double effectiveThreshold, Connection connection) throws HitErrorsThresholdException {
        TradeRWFile tradeRWFile = new TradeRWFile(connection);
        tradeRWFile.readFile(filePath, effectiveThreshold);
    }

    @Override
    public void readFile(String filePath, Double effectiveThreshold) throws HitErrorsThresholdException {
        List<String[]> validTrades = new ArrayList<>();
        int totalRows = 0;
        int errorCount = 0;

        try (FileWriter errorLogWriter = new FileWriter("src/problems/tradeoperations/extrausedfiles/error_read_log.txt", true);
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            TradeValidator validator = new TradeValidator(errorLogWriter);
            String line;
            while ((line = br.readLine()) != null) {
                totalRows++;
                // Skip header line
                if (line.startsWith("trade_id")) continue;
                String[] fields = line.split(",");
                if (!validator.isValidRow(fields, line)) {
                    errorCount++;
                    continue;
                }
                System.out.println("Valid row: " + line);
                validTrades.add(fields);
            }
            validateErrorThreshold(effectiveThreshold, totalRows, errorCount);
            int[] result = processBatchInsert(validTrades);
            summarizeResults(totalRows, validTrades.size(), errorCount, result[1]);
        } catch (HitErrorsThresholdException e) {
            handleErrorThreshold(totalRows, errorCount, validTrades.size());
            throw e;
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    private void validateErrorThreshold(Double effectiveThreshold, int totalRows, int errorCount) throws HitErrorsThresholdException {
        if (errorCount > totalRows * (effectiveThreshold / 100)) {
            System.out.println("******** Error Threshold *********");
            System.out.println("ErrorThreshold_Total_After_Arithmetic_Operation: " + (totalRows * (effectiveThreshold / 100)));
            System.out.println("Error threshold exceeded: " + errorCount + " out of " + totalRows + " rows failed.");
            throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " out of " + totalRows + " rows failed.");
        }
    }

    public static void summarizeResults(int totalRows, int validTradesSize, int errorCount, int failedCount) {
        System.out.println("Summary:");
        System.out.println("Total rows processed: " + totalRows);
        System.out.println("Valid rows inserted: " + validTradesSize);
        System.out.println("Failed rows due to validation errors: " + errorCount);
        System.out.println("Failed rows during insertion: " + failedCount);
    }

    private void handleErrorThreshold(int totalRows, int errorCount, int validTradesSize) {
        System.out.println("Summary:");
        System.out.println("Total rows processed: " + totalRows);
        System.out.println("Valid rows inserted: 0"); // No successful inserts due to threshold
        System.out.println("Failed rows due to validation errors: " + errorCount);
        System.out.println("Failed rows during batch insertion: " + validTradesSize); // All valid rows failed due to threshold
    }

    @Override
    public int[] processBatchInsert(List<String[]> validTrades) {
//        DatabaseManager databaseManager = new DatabaseManager();
//        databaseManager.configureHikariCP();
        String insertQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (
             PreparedStatement statement = connection.prepareStatement(insertQuery);
             FileWriter errorLogWriter = new FileWriter("src/problems/tradeoperations/extrausedfiles/error_write_log.txt", true)) {

            connection.setAutoCommit(false); // Start transaction

            int successfulInserts = 0;
            int failedInserts = 0;

            for (String[] fields : validTrades) {
                if (!insertTrade(fields, statement, errorLogWriter)) {
                    failedInserts++;
                } else {
                    successfulInserts++;
                }
            }

            try {
                // Execute batch insert
                int[] result = statement.executeBatch();
                connection.commit(); // Commit transaction if all statements succeed

                // Count successful and failed inserts from the result
                for (int res : result) {
                    if (res >= 0) successfulInserts++;
                    else failedInserts++;
                }
                System.out.println("Inserted valid trades into the database.");
                return new int[]{successfulInserts, failedInserts};

            } catch (SQLException batchException) {
                connection.rollback(); // Rollback if there is an error during batch execution
                System.out.println("Failed to insert trade into the database: " + batchException.getMessage());
                failedInserts += validTrades.size(); // Assuming all valid trades failed
                return new int[]{successfulInserts, failedInserts}; // Return the result
            }

        } catch (SQLException e) {
            System.out.println("Error with database connection: " + e.getMessage());
            return new int[]{0, validTrades.size()}; // Return all failed on error
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the log file.", e);
        }
    }

    // Helper method for inserting a single trade
    private boolean insertTrade(String[] fields, PreparedStatement statement, FileWriter errorLogWriter) throws SQLException {
        String tickerSymbol = fields[2];
        String line = fields[0];

        // Validate security symbol
        if (!isValidSecurity(tickerSymbol, statement.getConnection())) {
            logInvalidSymbol(line, tickerSymbol, errorLogWriter);
            return false; // Insert failed
        }

        try {
            statement.setString(1, fields[0]);
            statement.setString(2, fields[1]);
            statement.setString(3, fields[2]);
            statement.setInt(4, Integer.parseInt(fields[3]));
            statement.setDouble(5, Double.parseDouble(fields[4]));
            statement.setString(6, fields[5]);
            statement.addBatch();
            return true; // Insert succeeded
        } catch (SQLException | NumberFormatException e) {
            logInvalidData(line, e.getMessage(), errorLogWriter);
            return false; // Insert failed
        }
    }

    private void logInvalidSymbol(String line, String tickerSymbol, FileWriter errorLogWriter) {
        try {
            String errorMessage = "Line: " + line + " | Invalid security symbol: " + tickerSymbol;
            errorLogWriter.write(errorMessage + System.lineSeparator());
            errorLogWriter.flush(); // Ensure immediate write
        } catch (IOException e) {
            System.out.println("Failed to log error: " + e.getMessage());
        }
    }

    private void logInvalidData(String line, String errorMsg, FileWriter errorLogWriter) {
        try {
            String errorMessage = "Line: " + line + " | Data error: " + errorMsg;
            errorLogWriter.write(errorMessage + System.lineSeparator());
            errorLogWriter.flush(); // Ensure immediate write
        } catch (IOException e) {
            System.out.println("Failed to log error: " + e.getMessage());
        }
    }

    private boolean isValidSecurity(String symbol, Connection connection) throws SQLException {
        String lookupQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        try (PreparedStatement lookupStmt = connection.prepareStatement(lookupQuery)) {
            lookupStmt.setString(1, symbol);
            return lookupStmt.executeQuery().next();
        }
    }

}
