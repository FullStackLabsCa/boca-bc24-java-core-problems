package problems.jdbc.trading.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.service.TradeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TradeRepository {

    public static void insertTrade(List<Trade> trades, HikariDataSource dataSource, int errorCount, double threshold,
                                   int records) throws HitErrorsThresholdException, SQLException {
        String query = "Insert into Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, " +
                "trade_date) values(?, ?, ?, ?, ?, ?)";
        int inserts = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(query);
            int batchSize = 50;
            int batchNumber = 0;
            for (Trade trade : trades) {
                if (checkSecurities(connection, trade.getTickerSymbol())) {
                    stmt.setString(1, trade.getTradeId());
                    stmt.setString(2, trade.getTradeIdentifier());
                    stmt.setString(3, trade.getTickerSymbol());
                    stmt.setInt(4, trade.getQuantity());
                    stmt.setDouble(5, trade.getPrice());
                    stmt.setDate(6, trade.getTradeDate());
                    stmt.addBatch();
                    batchNumber++;
                    if (trades.indexOf(trade) == trades.size() - 1 || batchNumber == batchSize) {
                        batchNumber = 0;
                        stmt.executeBatch();
                    }
                } else {
                    errorCount++;
                    double errorRate = ((double) errorCount / records) * 100;
                    if (errorRate > threshold) {
                        throw new HitErrorsThresholdException("Insertion failed...");
                    }
                }
            }
            connection.commit();
            inserts = records - errorCount;
        } catch (SQLException | HitErrorsThresholdException e) {
            if (connection != null) connection.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) connection.setAutoCommit(true);
            TradeService.printSummary(records, inserts, errorCount);
        }
    }

    public static boolean checkSecurities(Connection connection, String symbol) throws SQLException {
        boolean exists = false;
        try {
            String query = "Select 1 from SecuritiesReference where symbol = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, symbol);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return exists;
    }
}
