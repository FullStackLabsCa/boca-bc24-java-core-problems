package problems.jdbc.trading.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.service.TradeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class TradeRepository {

    public static void insertTrade(Map<Integer, Trade> trades, HikariDataSource dataSource) throws HitErrorsThresholdException, SQLException {
        String query = "Insert into Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, " +
                "trade_date) values(?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(query);
            int batchSize = 50;
            int batchNumber = 0;
            ArrayList<Integer> recordNumbers = new ArrayList<>(trades.keySet());
            for (Integer recordNumber : recordNumbers) {
                if (checkSecurities(connection, trades.get(recordNumber).getTickerSymbol())) {
                    stmt.setString(1, trades.get(recordNumber).getTradeId());
                    stmt.setString(2, trades.get(recordNumber).getTradeIdentifier());
                    stmt.setString(3, trades.get(recordNumber).getTickerSymbol());
                    stmt.setInt(4, trades.get(recordNumber).getQuantity());
                    stmt.setDouble(5, trades.get(recordNumber).getPrice());
                    stmt.setDate(6, trades.get(recordNumber).getTradeDate());
                    stmt.addBatch();
                    batchNumber++;
                    if (recordNumbers.size() - 1 == recordNumbers.indexOf(recordNumber) || batchNumber == batchSize) {
                        batchNumber = 0;
                        stmt.executeBatch();
                    }
                } else {
                    ErrorChecking.incrementErrorCount();
                    TradeService.writeErrorLog("/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src" +
                                    "/problems/jdbc/trading/logs/writerErrorLog.txt",
                            new Date() + "Insertion error on line " + recordNumber + " -> ERROR: Invalid ticker_symbol.");
                    if (TradeService.isThresholdExceeded()) {
                        connection.rollback();
                        throw new HitErrorsThresholdException("Insertion failed...Threshold limit reached.");
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) connection.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) connection.setAutoCommit(true);
        }
    }

    public static boolean checkSecurities(Connection connection, String symbol) {
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
