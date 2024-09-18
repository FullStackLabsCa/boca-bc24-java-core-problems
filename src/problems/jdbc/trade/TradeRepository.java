package problems.jdbc.trade;

import problems.jdbc.hikari.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class TradeRepository {

    static Connection connection;

    static {
        try {
            connection = DataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TradeRepository() throws Exception {
    }

    // Insert a new account for a credit card number
    public static void insertTrade(List<TradeTransaction> tradeTransactions, int errorCounter) throws SQLException {
        connection.setAutoCommit(false);
        String insertQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);

        try {
            for (TradeTransaction tradeTransaction : tradeTransactions) {
                System.out.println("Inserting id" + tradeTransaction.getTradeId());
                stmt.setString(1, tradeTransaction.getTradeId());
                stmt.setString(2, tradeTransaction.getTickerSymbol());
                stmt.setInt(3, tradeTransaction.getQuantity());
                stmt.setDouble(4, tradeTransaction.getPrice());
                stmt.setDate(5, new Date(tradeTransaction.getTradeDate().getTime()));
                stmt.addBatch();
            }
            int[] ints = stmt.executeBatch();
            System.out.println("Number of rows inserted " + ints.length);
        } catch (HitErrorsThresholdException e) {
            connection.rollback();
            //handle here checked exception after comparing the offset file;
            throw new HitErrorsThresholdException("Threshold Error exception...");
        }
        System.out.println("Batch Insertion Successfully");
        connection.commit();
        connection.setAutoCommit(true);
    }
}
