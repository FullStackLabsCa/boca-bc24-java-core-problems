package problems.trading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeRepository {
    public static void insertQuery(LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) throws Exception {
        Connection connection = DatabaseConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

            String insertQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
            String lookupQuery = "SELECT symbol FROM SecuritiesReference WHERE symbol = ?";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            PreparedStatement lookupStatement = connection.prepareStatement(lookupQuery);

            for (TradeTransaction tradeTransaction : tradingTransactionDeQueue) {
                String symbol = "";
                ResultSet resultSet = getResultSetForLookupQuery(tradeTransaction, lookupStatement);
                if (resultSet.next()) {
                    symbol = resultSet.getString("symbol");
                }

                if (symbol.equals(tradeTransaction.getTickerSymbol())) {
                    preparingStatementForBatch(tradeTransaction, statement);
                    statement.addBatch();
                }
            }

            statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    private static ResultSet getResultSetForLookupQuery(TradeTransaction tradeTransaction, PreparedStatement lookupStatement) throws SQLException {
        lookupStatement.setString(1, tradeTransaction.getTickerSymbol());
        ResultSet resultSet = lookupStatement.executeQuery();
        return resultSet;
    }

    private static void preparingStatementForBatch(TradeTransaction tradeTransaction, PreparedStatement statement) throws SQLException {
        statement.setString(1, tradeTransaction.getTradeId());
        statement.setString(2, tradeTransaction.getTradeIndentifier());
        statement.setString(3, tradeTransaction.getTickerSymbol());
        statement.setInt(4, tradeTransaction.getQuantity());
        statement.setDouble(5, tradeTransaction.getPrice());
        statement.setDate(6, tradeTransaction.getTradeDate());
    }
}
