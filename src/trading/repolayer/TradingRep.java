package trading.repolayer;

import trading.model.TradingValues;
import trading.utility.HitErrorsThresholdException;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static trading.presentationLayer.TradingRunner.thresholdValue;

@SuppressWarnings("squid:S106")
public class TradingRep {
    private TradingRep() {
    }

    public static void insertData(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException, HitErrorsThresholdException {
        final int Batch_Size = 5;
        int invalidRows = 0;
        int validRows = 0;

        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);

                for (TradingValues tradingValues : tradingValuesList) {
                    String tickerSymbol = tradingValues.getTicker_symbol();
                    if (!selectData(connection, tickerSymbol)) {
                        String errorMessage = "Invalid Ticker symbol :- " +
                                "trade_id: " + tradingValues.getTrade_id() + ", " +
                                "trade_identifier: " + tradingValues.getTrade_identifier() + ", " +
                                "ticker_symbol: " + tradingValues.getTicker_symbol() + ", " +
                                "quantity: " + tradingValues.getQuantity() + ", " +
                                "price: " + tradingValues.getPrice() + ", " +
                                "trade_date: " + tradingValues.getTrade_date();

                        invalidRows++;
                        errorLog(errorMessage);
                        System.out.println(errorMessage);
                        continue;
                    }

                    try {
                        preparedStatement.setInt(1, Integer.parseInt(tradingValues.getTrade_id()));
                        preparedStatement.setString(2, tradingValues.getTrade_identifier());
                        preparedStatement.setString(3, tradingValues.getTicker_symbol());
                        preparedStatement.setInt(4, tradingValues.getQuantity());
                        preparedStatement.setDouble(5, tradingValues.getPrice());
                        preparedStatement.setDate(6, Date.valueOf(tradingValues.getTrade_date()));
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

                double percentage = ((double) invalidRows / (validRows)) * 100.00;

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
            System.out.println(" error during database operation: " + e.getMessage());
        }
    }

    public static void errorLog(String error) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/Gagandeep.Kaur/source/students/boca-bc24-java-core-problems/src/Trading/utility/write_error.txt", true))) {
            bufferedWriter.write(error);
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
            boolean exists;
            exists = rs.next();
            return exists;
        } catch (SQLException e) {
            System.out.println("SQL error during data selection: " + e.getMessage());
        }
        return false;
    }
}
