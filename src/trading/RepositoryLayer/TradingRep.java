package trading.RepositoryLayer;
import com.zaxxer.hikari.HikariDataSource;
import trading.Model.TradingValues;
import trading.Utility.HitErrorsThresholdException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static trading.PresentationLayer.TradingRunner.thresholdValue;

public class TradingRep {
    public static void insertdata(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException, HitErrorsThresholdException {
        final int Batch_Size = 5;
        int invalidRows = 0;
        int validRows = 0;

        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);

                for (TradingValues tradingValues : tradingValuesList) {
                    String tickerSymbol = tradingValues.getTickerSymbol();
                    if (!selectData(connection, tickerSymbol)) {
                        String errorMessage = "Invalid Ticker symbol :- " +
                                "trade_id: " + tradingValues.getTradeId() + ", " +
                                "trade_identifier: " + tradingValues.getTradeIdentifier() + ", " +
                                "ticker_symbol: " + tradingValues.getTickerSymbol() + ", " +
                                "quantity: " + tradingValues.getQuantity() + ", " +
                                "price: " + tradingValues.getPrice() + ", " +
                                "trade_date: " + tradingValues.getTradeDate();

                        invalidRows++;
                        errorLog(errorMessage);
                        System.out.println(errorMessage);
                        continue;
                    }

                    try {
                        preparedStatement.setInt(1, Integer.parseInt(tradingValues.getTradeId()));
                        preparedStatement.setString(2, tradingValues.getTradeIdentifier());
                        preparedStatement.setString(3, tradingValues.getTickerSymbol());
                        preparedStatement.setInt(4, tradingValues.getQuantity());
                        preparedStatement.setDouble(5, tradingValues.getPrice());
                        preparedStatement.setDate(6, Date.valueOf(tradingValues.getTradeDate()));
                        preparedStatement.addBatch();
                        validRows++;

                        if (validRows % Batch_Size == 0) {
                            preparedStatement.executeBatch();
                        }
                    } catch (SQLException e) {
                        errorLog("SQL Error while inserting data: " + e.getMessage());
                        System.out.println("SQL Error: " + e.getMessage());
                        invalidRows++;
                    }
                }
                if (validRows % Batch_Size != 0) {
                    preparedStatement.executeBatch();
                }

                double percentage = (double) invalidRows / ( validRows) * 100.00;
                if (percentage < thresholdValue) {
                    throw new HitErrorsThresholdException("Error threshold exceeded: " + invalidRows + " rows.");
                }
                connection.commit();
                System.out.println(validRows + " rows inserted successfully.");
                System.out.println(invalidRows +" rows are having invalid ticker Symbol");

            } catch (SQLException e) {
                connection.rollback();
                errorLog("Transaction rollback due to: " + e.getMessage());
                System.out.println("Transaction rollback due to: " + e.getMessage());

            }
        } catch (IOException e) {
            throw new RuntimeException(" error during database operation: " + e.getMessage());
        }
    }

//    public static void errorLog(String error) throws IOException {
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt", true))) {
//            bufferedWriter.write(error);
//            bufferedWriter.newLine();
//        } catch (Exception e) {
//            System.out.println("Error writing into log: " + e.getMessage());
//        }
//    }


//    public static void errorLog(String error) throws IOException {
//        String logFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt";
//
//        int index = 1;
//        try {
//            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(logFilePath));
//            index = lines.size() + 1;
//        } catch (IOException e) {
//
//            System.out.println("Could not read log file, starting index from 1.");
//        }
//
//        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
//        String logEntry = String.format("[%s] Error #%d: %s", timestamp, index, error);
//
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
//            bufferedWriter.write(logEntry);
//            bufferedWriter.newLine();
//        } catch (Exception e) {
//            System.out.println("Error writing into log: " + e.getMessage());
//        }
//    }

    private static int errorIndex = 1; // Default starting index

    static {
        String logFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt";
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(logFilePath));
            if (!lines.isEmpty()) {
                String lastLine = lines.get(lines.size() - 1);
                // Split the line at the error message part
                String[] parts = lastLine.split("-->ERROR:");
                if (parts.length > 0) {
                    // Extract the part before the error message
                    String beforeError = parts[0].trim();
                    // Split by spaces to find the last element
                    String[] beforeErrorParts = beforeError.split(" ");
                    // Assuming the last segment before the error message contains the index
                    if (beforeErrorParts.length >= 5) {
                        String lineNumberPart = beforeErrorParts[beforeErrorParts.length - 1]; // This should be the index
                        try {
                            errorIndex = Integer.parseInt(lineNumberPart) + 1; // Increment the last index
                        } catch (NumberFormatException e) {
                            System.out.println("Could not parse line number, starting index from 1.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read log file, starting index from 1.");
        }
    }

    public static void errorLog(String error) throws IOException {
        String logFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt";

        // Format timestamp to match the desired output
        String timestamp = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new java.util.Date());
        String logEntry = String.format("%s Insertion error on line %d-->ERROR: %s", timestamp, errorIndex++, error); // Increment index

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
            bufferedWriter.write(logEntry);
            bufferedWriter.newLine();
        } catch (Exception e) {
            System.out.println("Error writing into log: " + e.getMessage());
        }
    }

    public static boolean selectData(Connection connection, String tickerSymbol) {
        String query = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tickerSymbol);
            ResultSet rs = preparedStatement.executeQuery();
            boolean exists = rs.next();
            return exists;
        } catch (SQLException e) {
            throw new RuntimeException("SQL error during data selection: " + e.getMessage());
        }
    }
}
