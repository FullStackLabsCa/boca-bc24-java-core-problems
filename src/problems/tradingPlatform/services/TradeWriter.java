package problems.tradingPlatform.services;

import problems.tradingPlatform.databasehelpers.DatabaseConnection;
import problems.tradingPlatform.helpers.CommonFunctions;
import problems.tradingPlatform.helpers.ErrorManager;
import problems.tradingPlatform.interfaces.TradeWriterDao;
import problems.tradingPlatform.models.Trade;

import java.sql.*;
import java.util.List;

public class TradeWriter implements TradeWriterDao {



    @Override
    public int writeTradeData(List<Trade> tradeList,Connection conn,boolean forTest) {

         PreparedStatement preparedStatement = null;
         boolean autoCommit = false;
        String query = getQueryString(forTest);


        try {

            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(query);
            
           return writeDataInDatabase(tradeList,conn,preparedStatement,autoCommit);
        } catch (SQLException | IllegalArgumentException e) {
            try {
                conn.rollback();
                ErrorManager.checkForErrorThreshold("\n Transaction rolled back due to :" + e.getMessage(), false);
            } catch (SQLException rollbackException) {
                ErrorManager.checkForErrorThreshold("\n SQLException Occurred : " + rollbackException.getMessage(), false);
            }
            ErrorManager.checkForErrorThreshold("\n Connection Break Occurred : " + e.getMessage(),false);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(autoCommit);
                    conn.close();
                }
            } catch (SQLException e) {
                ErrorManager.checkForErrorThreshold("\n SQLException Occurred : " + e.getMessage(),false);
            }
        }
        return 0;
    }

    private static String getQueryString(boolean forTest) {
        String tableName;
        if (!forTest) tableName = "Trades_Test";
        else tableName = "Trades";
//         String query = """
//                INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date)
//                VALUES (?, ?, ?, ?, ?, ?)
//                ON DUPLICATE KEY UPDATE
//                    trade_identifier = VALUES(trade_identifier),
//                    ticker_symbol = VALUES(ticker_symbol),
//                    quantity = VALUES(quantity),
//                    price = VALUES(price),
//                    trade_date = VALUES(trade_date)""";

        String query = "INSERT INTO " + tableName + " (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "trade_identifier = VALUES(trade_identifier), " +
                "ticker_symbol = VALUES(ticker_symbol), " +
                "quantity = VALUES(quantity), " +
                "price = VALUES(price), " +
                "trade_date = VALUES(trade_date)";
        return query;
    }

    @Override
    public int writeDataInDatabase(List<Trade> tradeList,Connection conn,PreparedStatement preparedStatement,boolean autoCommit) throws SQLException {
        int totalUpdatedRows = 0;
        String lookupQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        PreparedStatement lookStmt = conn.prepareStatement(lookupQuery);

        int count = 0;
        if(tradeList != null) {
            for (Trade trade : tradeList) {
                lookStmt.setString(1, trade.getTickerSymbol());
                ResultSet resultSet = lookStmt.executeQuery();
                count++;
                if (resultSet.next()) {
                    preparedStatement.setInt(1, trade.getTradeId());
                    preparedStatement.setString(2, trade.getTradeIdentifier());
                    preparedStatement.setString(3, trade.getTickerSymbol());
                    preparedStatement.setInt(4, trade.getQuantity());
                    preparedStatement.setDouble(5, trade.getPrice());
                    preparedStatement.setDate(6, (Date) CommonFunctions.convertStringToDate(trade.getTradeDate()));
                    preparedStatement.addBatch();
                } else {
                    ErrorManager.checkForErrorThreshold( "Invalid Row as Security Symbol Does not Exist in Security Reference Table For Line : " + count, false);
                }
            }
            int[] batchResults = null;
            if (preparedStatement != null) {
                batchResults = preparedStatement.executeBatch();


                for (int result : batchResults) {
                    if (result > 0) {
                        totalUpdatedRows += result;
                    }
                }
                System.out.println("Batch executed and transaction committed successfully for " + totalUpdatedRows + " Rows");

                return totalUpdatedRows;
            }

            conn.commit();
        }else{
            throw new IllegalArgumentException();
        }
        if (lookStmt != null) {
            lookStmt.close();
        }
        return  totalUpdatedRows;
    }
}
