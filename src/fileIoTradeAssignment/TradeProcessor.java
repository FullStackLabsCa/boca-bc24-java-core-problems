package fileIoTradeAssignment;

import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import static fileIoTradeAssignment.DatabaseHelper.getConnection;

public class TradeProcessor {

    private String filePath;
    private ArrayList<TradePOJO> validLines;
    private static HikariDataSource dataSource = getConnection();

    private int errorCounter = 0;
    private int readFileErrorThreshold;
    private int DBInsertErrorThreshold;
    private final int BATCH_SIZE = 50;

    // Method to set error thresholds
    public void setErrorThreshold(double threshold) {
        this.readFileErrorThreshold = (int) threshold; // Assuming we cast to int
        this.DBInsertErrorThreshold = (int) threshold; // Assuming we cast to int
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void readTradeFile() throws FileNotFoundException, HitReadFileErrorsThresholdException {
        validLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String newLine;
            boolean isFirstLine = true;

            while ((newLine = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] tradeDetails = newLine.split(",");

                try {
                    // Parse details into TradePOJO
                    int trade_id = Integer.parseInt(tradeDetails[0]);
                    String trade_identifier = tradeDetails[1];
                    String ticker_symbol = tradeDetails[2];
                    int quantity = Integer.parseInt(tradeDetails[3]);
                    double price = Double.parseDouble(tradeDetails[4]);
                    Date trade_date = Date.valueOf(tradeDetails[5]);

                    // Create TradePOJO object
                    TradePOJO tradePOJO = new TradePOJO(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date);

                    // Validate the tradePOJO
                    if (validateTradePOJO(tradePOJO)) {
                        validLines.add(tradePOJO); // Store valid TradePOJO objects
                    }

                } catch (Exception e) {
                    errorCounter++;
                    String errorMessage = "Error parsing line: " + newLine + " - " + e.getMessage();
                    System.err.println(errorMessage);
                    logError(errorMessage, "readFile_error_log.txt");
                }

                if (errorCounter >= readFileErrorThreshold) {
                    String errorMessage = "Error count: " + errorCounter + " exceeds threshold while reading file.";
                    System.out.println(errorMessage);
                    logError(errorMessage, "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/fileIoTradeAssignment/errorLogs/readFile_error_log.txt");
                    throw new HitReadFileErrorsThresholdException("Threshold limit exceeded while reading file");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File doesn't exist");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateTradePOJO(TradePOJO trade) {
        // Add custom validation logic
        return trade.getTrade_id() > 0 && (trade.getQuantity() > 0 && trade.getQuantity() <= 500) && (trade.getPrice() > 0 && trade.getPrice() <= 4000.00);
    }

    public boolean checkTickerSymbol(String currentTicker) throws SQLException {
        String query = "SELECT COUNT(*) FROM SecuritiesReference WHERE symbol = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, currentTicker);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // If count is greater than 0, the ticker exists
                }
            }
        } catch (SQLException e) {
            String errorMessage = "Error checking ticker symbol: " + e.getMessage();
            System.err.println(errorMessage);
            logError(errorMessage, "DBInsertion_error_log.txt");
            throw e; // Propagate exception for further handling
        }

        return false; // If no match found
    }

    public void tradesInsertionMaker() throws SQLException, HitDatabaseInsertErrorsThresholdException {
        if (validLines.isEmpty()) {
            System.out.println("No valid trades to insert into the database.");
            return;
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            String query = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                for (TradePOJO tradePOJO : validLines) {

                    // Validate ticker symbol before adding to batch
                    if (!checkTickerSymbol(tradePOJO.getTicker_symbol())) {
                        errorCounter++;
                        String errorMessage = "Invalid ticker symbol: " + tradePOJO.getTicker_symbol();
                        System.err.println(errorMessage);
                        logError(errorMessage, "DBInsertion_error_log.txt");

                        if (errorCounter >= DBInsertErrorThreshold) {
                            throw new HitDatabaseInsertErrorsThresholdException("Error threshold limit reached during database insertion.");
                        }
                        continue; // Skip invalid trade
                    }

                    stmt.setInt(1, tradePOJO.getTrade_id());
                    stmt.setString(2, tradePOJO.getTrade_identifier());
                    stmt.setString(3, tradePOJO.getTicker_symbol());
                    stmt.setInt(4, tradePOJO.getQuantity());
                    stmt.setDouble(5, tradePOJO.getPrice());
                    stmt.setDate(6, tradePOJO.getDate());

                    stmt.addBatch(); // Add only valid trades to batch

                    // Execute batch if it reaches the BATCH_SIZE
                    if (stmt.getUpdateCount() % BATCH_SIZE == 0) {
                        stmt.executeBatch();
                        conn.commit();
                    }
                }

                // Execute any remaining batch
                stmt.executeBatch();
                conn.commit();
                System.out.println("Valid trades have been inserted into the database.");

            } catch (SQLException e) {
                conn.rollback();
                errorCounter++;
                String errorMessage = "Error during batch insertion: " + e.getMessage();
                System.err.println(errorMessage);
                logError(errorMessage, "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/fileIoTradeAssignment/errorLogs/DBInsertion_error_log.txt");
                throw e;
            }
        }
    }

    private void logError(String message, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.write(message);
            writer.println();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}