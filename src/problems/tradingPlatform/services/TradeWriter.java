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
    public void writeTradeData(List<Trade> tradeList, double errorThresholdCount) {

         Connection conn = null;
         PreparedStatement preparedStatement = null;
         boolean autoCommit = false;

         String query = """
                INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    trade_identifier = VALUES(trade_identifier),
                    ticker_symbol = VALUES(ticker_symbol),
                    quantity = VALUES(quantity),
                    price = VALUES(price),
                    trade_date = VALUES(trade_date)""";

        try {
            conn = DatabaseConnection.getConnection();
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(query);
            
            writeDataInDatabase(tradeList,conn,preparedStatement,autoCommit);
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n Transaction rolled back due to :"+e.getMessage(),false);
                } catch (SQLException rollbackException) {
                    ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n SQLException Occurred : " + rollbackException.getMessage(),false);
                }
            }
            ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n Connection Break Occurred : " + e.getMessage(),false);
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
                ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n SQLException Occurred : " + e.getMessage(),false);
            }
        }
    }

    @Override
    public void writeDataInDatabase(List<Trade> tradeList,Connection conn,PreparedStatement preparedStatement,boolean autoCommit) throws SQLException {
        String lookupQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        PreparedStatement lookStmt = conn.prepareStatement(lookupQuery);

        for (Trade trade : tradeList) {
            lookStmt.setString(1,trade.getTickerSymbol());
            ResultSet resultSet =lookStmt.executeQuery();
            if(resultSet.next()) {
                preparedStatement.setInt(1, trade.getTradeId());
                preparedStatement.setString(2, trade.getTradeIdentifier());
                preparedStatement.setString(3, trade.getTickerSymbol());
                preparedStatement.setInt(4, trade.getQuantity());
                preparedStatement.setDouble(5, trade.getPrice());
                preparedStatement.setDate(6, (Date) CommonFunctions.convertStringToDate(trade.getTradeDate()));
                preparedStatement.addBatch();
            }

        }
        int[] batchResults = null;
        if (preparedStatement != null) {
            batchResults = preparedStatement.executeBatch();
            int totalUpdatedRows = 0;

            for (int result : batchResults) {
                if (result > 0) {
                    totalUpdatedRows += result;
                }
            }
            System.out.println("Batch executed and transaction committed successfully for " +totalUpdatedRows + " Rows");
        }
        conn.commit();
    }
}
