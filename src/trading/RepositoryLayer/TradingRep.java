//package trading.RepositoryLayer;
//import com.zaxxer.hikari.HikariDataSource;
//import trading.Model.TradingValues;
//import trading.Utility.HitErrorsThresholdException;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.*;
//import java.util.List;
//
//import static trading.PresentationLayer.TradingRunner.thresholdValue;
//
//public class TradingRep {
//    static int parsingSuccess = 0, passingFailure = 0;
//    static int insertionSuccess = 0, insertionFailure = 0;
//
//    public static void insertdata(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException {
//        final int Batch_Size = 5;
//        int invalidRows = 0;
//        int validRows = 0;
//        Connection connection = dataSource.getConnection();
//
//        String query = "Insert into Trades(trade_id,trade_identifier,ticker_symbol,quantity,price,trade_date) VALUES(?,?,?,?,?,?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            connection.setAutoCommit(false);
//         //   int counter = 0;
//            for (TradingValues tradingValues : tradingValuesList) {
//                //   counter++;
//                String tickerSymbol = tradingValues.getTickerSymbol();
//                if (!selectData(connection, tickerSymbol)) {
//                    String errorMessage = tickerSymbol + "Invalid Ticker symbol: ";
//                    invalidRows++;
//                    errorLog(errorMessage);
//                    System.out.println(errorMessage);
//                    continue;
//                }
//                preparedStatement.setInt(1, Integer.parseInt(tradingValues.getTradeId()));
//                preparedStatement.setString(2, tradingValues.getTradeIdentifier());
//                preparedStatement.setString(3, tradingValues.getTickerSymbol());
//                preparedStatement.setInt(4, tradingValues.getQuantity());
//                preparedStatement.setDouble(5, tradingValues.getPrice());
//                preparedStatement.setDate(6, Date.valueOf((tradingValues.getTradeDate())));
//                preparedStatement.addBatch();
//                validRows++;
//                if (validRows % Batch_Size==0 ) {
//                    preparedStatement.executeBatch();
//                }
//            }
//            if (validRows % Batch_Size != 0) {
//                preparedStatement.executeBatch();
//            }
//            double percentage = (double) invalidRows / (invalidRows + validRows) * 100.00;
//            if (percentage > thresholdValue) {
//                throw new HitErrorsThresholdException("Error threshold exceeded: " + invalidRows + " rows.");
//            }
//
//          connection.commit();
//            System.out.println(validRows+" rows inserted sucessfully..");// 5 valid rows
//            System.out.println(invalidRows);// 1 rows it should be 10
//
//        } catch (SQLException | HitErrorsThresholdException e) {
//            try {
//                connection.rollback();
//                System.out.println("Transaction rollback....");
//                throw new RuntimeException("Database error: "+e.getMessage());
//
//            } catch (Exception ex) {
//                throw new RuntimeException("Rollback Failed :");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Sucessfully inserted row: " + parsingSuccess); //giving me 9 rows
//        System.out.println("Failure Rows : " + passingFailure);// falure rows
//        connection.commit();
//    }
//
//    public static void errorLog(String error) throws IOException {
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt", true))) {
//            bufferedWriter.write(error);
//            bufferedWriter.newLine();
//        } catch (Exception e) {
//            System.out.println("Error writing into log:");
//        }
//    }
//    public static boolean selectData(Connection connection, String tickerSymbol) {
//        String query = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            connection.setAutoCommit(false);
//            preparedStatement.setString(1, tickerSymbol);
//            ResultSet rs = preparedStatement.executeQuery();
//            boolean exists = rs.next();
//            if (exists) {
//                parsingSuccess++;
//            } else {
//                passingFailure++;
//            }
//            return exists;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
////public static void insertionCount(int[] excuteQuery) {
////    for (int count : excuteQuery) {
////        switch (count) {
////            case 1:
////                insertionSuccess++;
////                break;
////            case -2:
////            case -3:
////                insertionFailure++;
////                break;
////        }
////    }
////}
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
            throw new RuntimeException("IO error during database operation: " + e.getMessage());
        }
    }


    public static void errorLog(String error) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt", true))) {
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
            boolean exists = rs.next();
            return exists;
        } catch (SQLException e) {
            throw new RuntimeException("SQL error during data selection: " + e.getMessage());
        }
    }
}
