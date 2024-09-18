package problems.trading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeRepository {
    public static void insertQuery(LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) throws Exception {
        Connection connection = DatabaseConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

            String insertQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            for (TradeTransaction tradeTransaction : tradingTransactionDeQueue) {
                statement.setString(1, tradeTransaction.getTradeId());
                statement.setString(2, tradeTransaction.getTickerSymbol());
                statement.setInt(3, tradeTransaction.getQuantity());
                statement.setDouble(4, tradeTransaction.getPrice());
                statement.setDate(5, tradeTransaction.getTradeDate());
                statement.addBatch();
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
}
