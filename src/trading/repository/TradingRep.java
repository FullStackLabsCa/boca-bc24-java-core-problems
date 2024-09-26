package trading.repository;

import com.zaxxer.hikari.HikariDataSource;
import trading.model.TradingValues;
import trading.utility.HitErrorsThresholdException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static trading.presentation.TradingRunner.THRESHOLD_VALUE;

public class TradingRep {
    private TradingRep(){
        throw new IllegalStateException("Utility class");
    }
    private static int errorIndex = 1;

    public static void insertdata(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException, HitErrorsThresholdException, IOException {

        final int Batch_Size = 50;
        int invalidRows = 0;
        int validRows = 0;

        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);

                for (TradingValues tradingValues : tradingValuesList) {
                    String tickerSymbol = tradingValues.getTickerSymbol();
                    int currentIndex = tradingValuesList.indexOf(tradingValues) + 1;
                    if (!selectData(connection, tickerSymbol)) {
                        String errorMessage = "Invalid Ticker symbol :- " +
                                "trade_id: " + tradingValues.getTradeId() + ", " +
                                "trade_identifier: " + tradingValues.getTradeIdentifier() + ", " +
                                "ticker_symbol: " + tradingValues.getTickerSymbol() + ", " +
                                "quantity: " + tradingValues.getQuantity() + ", " +
                                "price: " + tradingValues.getPrice() + ", " +
                                "trade_date: " + tradingValues.getTradeDate();

                        invalidRows++;
                        errorLogWriter(errorMessage, currentIndex);
                        System.out.println(errorMessage);
                        errorIndex++;
                        continue;
                    }

                        preparedStatement.setInt(1, Integer.parseInt(tradingValues.getTradeId()));
                        preparedStatement.setString(2, tradingValues.getTradeIdentifier());
                        preparedStatement.setString(3, tradingValues.getTickerSymbol());
                        preparedStatement.setInt(4, tradingValues.getQuantity());
                        preparedStatement.setDouble(5, tradingValues.getPrice());
                        preparedStatement.setDate(6, Date.valueOf(tradingValues.getTradeDate()));
                        preparedStatement.addBatch();
                        validRows++;
                }
                if (validRows % Batch_Size != 0) {
                    preparedStatement.executeBatch();
                }

               double percentage = 0;
                if(validRows > 0){
                    percentage = (double) invalidRows / (validRows) * 100.00;
                }else{
                    throw  new ArithmeticException("Values cant be divided by zero..");
                }
                if (percentage < THRESHOLD_VALUE) {
                    throw new HitErrorsThresholdException("Error threshold exceeded: " + invalidRows + " rows.");
                }
                connection.commit();
                System.out.println(validRows + " rows inserted successfully.");
                System.out.println(invalidRows + " rows are having invalid ticker Symbol");

            } catch (SQLException e) {
                connection.rollback();
                errorLogWriter("Transaction rollback due to: " + e.getMessage(), -1);
                System.out.println("Transaction rollback due to: " + e.getMessage());

            }
        }
    }
    public static void errorLogWriter(String error, int errorIndex) throws IOException {
        String logFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/Writer_Error.txt";
        String timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new java.util.Date());
        String logEntry = String.format("%s Insertion error on line %d --> ERROR: %s", timestamp, errorIndex, error);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true))) {
            bufferedWriter.write(logEntry);
            bufferedWriter.newLine();
        } catch (Exception e) {
            System.out.println("Error writing into log: " + e.getMessage());
        }
    }

    public static boolean selectData(Connection connection, String tickerSymbol) throws SQLException {
        String query = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tickerSymbol);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
    }
}
