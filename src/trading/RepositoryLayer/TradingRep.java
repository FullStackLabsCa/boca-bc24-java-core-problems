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
    static int parsingSuccess = 0, passingFailure = 0;
    static int insertionSuccess = 0, insertionFailure = 0;

    public static void insertdata(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException {
        final int Batch_Size = 5;
        int invalidRows = 0;
        int validRows = 0;
        Connection connection = dataSource.getConnection();

        String query = "Insert into Trades(trade_id,trade_identifier,ticker_symbol,quantity,price,trade_date) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            int counter = 0;
            for (TradingValues tradingValues : tradingValuesList) {
                counter++;
                String tickerSymbol = tradingValues.getTickerSymbol();
                if (!selectData(connection, tickerSymbol)) {
                    String errorMessage = "Ticker symbol doesn't exist: " + tickerSymbol;
                    errorLog(errorMessage);
                    System.out.println(errorMessage);
                    invalidRows++;
                    continue;
                }
                if (tradingValues.getTradeDate() == null) {
                    String errorMessage = "Invalid Date Format for trade ID: " + tradingValues.getTradeId();
                    errorLog(errorMessage);
                    System.out.println(errorMessage);
                    invalidRows++;
                    continue;
                }

                    preparedStatement.setInt(1, Integer.parseInt(tradingValues.getTradeId()));
                    preparedStatement.setString(2, tradingValues.getTradeIdentifier());
                    preparedStatement.setString(3, tradingValues.getTickerSymbol());
                    preparedStatement.setInt(4, tradingValues.getQuantity());
                    preparedStatement.setDouble(5, tradingValues.getPrice());
                    preparedStatement.setDate(6, Date.valueOf((tradingValues.getTradeDate())));
                    preparedStatement.addBatch();

                    if (validRows==Batch_Size || (counter==tradingValuesList.size())) {
                        preparedStatement.executeBatch();
                    }
                    if (validRows > 0) {
                        preparedStatement.executeBatch();
                    }
                    validRows++;
              double percentage = ((invalidRows/(invalidRows+validRows))*100);
                if (percentage > thresholdValue) {
                    throw new HitErrorsThresholdException("Error threshold exceeded: " + invalidRows + " rows.");
                }
                connection.commit();

                try {
                    if (validRows == 50) {
                        preparedStatement.executeBatch();
                        //    insertionSuccess=insertionSuccess+50;
                    }
                } catch (BatchUpdateException e) {
                    int[] updateCounts = e.getUpdateCounts();
                    insertionCount(updateCounts);
                }
            }
            int[] ints = preparedStatement.executeBatch();

            connection.commit();

        } catch (SQLException | HitErrorsThresholdException e) {
            try {
                connection.rollback();
                System.out.println("Transaction rollback :");

            } catch (Exception ex) {
                throw new RuntimeException("Rollback Failed :");
            }
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sucessfully inserted row: " + parsingSuccess);
        System.out.println("Failure Rows : " + passingFailure);
    }

    public static void errorLog(String error) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt", true))) {
            bufferedWriter.write(error);
            bufferedWriter.newLine();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static boolean selectData(Connection connection, String tickerSymbol) {
        String query = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        boolean validation = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, tickerSymbol);
            ResultSet rs = preparedStatement.executeQuery();
            boolean exists = rs.next();
            if (exists) {
                parsingSuccess++;
            } else {
                passingFailure++;
            }
            return exists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertionCount(int[] excuteQuery) {
        for (int count : excuteQuery) {
            switch (count) {
                case 1:
                    insertionSuccess++;
                    break;
                case -2:
                case -3:
                    insertionFailure++;
                    break;
            }
        }
    }

}
