package thread.tradeprocess.repository;

import jdbc.optimisticlocking.exceptions.OptimisticLockingException;
import thread.tradeprocess.model.TradeProcessData;

import java.sql.*;

public class TradeRepository {
    private TradeRepository() {}

    public static String getTradeId(Connection connection, String tradeId) throws SQLException {
        String selectQuery = "SELECT trade_id FROM trade_payloads WHERE trade_id = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, tradeId);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("trade_id");
        }
        return null;
    }

    public static void insertTradePayload(Connection connection, String tradeId, String status, String statusReason, String tradePayload) throws SQLException {
        connection.setAutoCommit(false);
        String insertQuery = "INSERT INTO trade_payloads(trade_id, status, status_reason, payload) VALUES(?,?,?,?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, tradeId);
        insertStatement.setString(2, status);
        insertStatement.setString(3, statusReason);
        insertStatement.setString(4, tradePayload);
        insertStatement.executeUpdate();
        connection.commit();
    }

    public static String getTradePayload(Connection connection, String tradeId) throws SQLException {
        String selectQuery = "SELECT payload FROM trade_payloads WHERE trade_id = ? AND status = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, tradeId);
        selectStatement.setString(2, "valid");
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("payload");
        }
        return null;
    }

    public static String getCusip(Connection connection, String cusip) throws SQLException {
        String selectQuery = "SELECT cusip FROM securities_reference WHERE cusip = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, cusip);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("cusip");
        }
        return null;
    }

    public static void insertJournalEntry(Connection connection, TradeProcessData tradeProcessData) throws SQLException {
        connection.setAutoCommit(false);
        String insertQuery = "INSERT INTO journal_entry(account_number, security, direction, quantity) VALUES(?,?,?,?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, tradeProcessData.getAccountNo());
        insertStatement.setString(2, tradeProcessData.getCusip());
        insertStatement.setString(3, tradeProcessData.getActivity());
        insertStatement.setInt(4, tradeProcessData.getQuantity());
        insertStatement.executeUpdate();
        connection.commit();
    }

    public static int getPositionVersion(Connection connection, String accountNo, String cusip) throws SQLException {
        String selectQuery = "SELECT version FROM positions WHERE account_number = ? AND security = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, accountNo);
        selectStatement.setString(2, cusip);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("version");
        }
        return -1;
    }

    public static int getPosition(Connection connection, String accountNo, String cusip) throws SQLException {
        String selectQuery = "SELECT postion FROM positions WHERE account_number = ? AND security = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, accountNo);
        selectStatement.setString(2, cusip);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("position");
        }
        return 0;
    }

    public static void insertPosition(Connection connection, TradeProcessData tradeProcessData) throws SQLException {
        connection.setAutoCommit(false);
        int quantity = tradeProcessData.getQuantity();
        String insertQuery = "INSERT INTO positions (account_number, security, position, version) VALUES (?, ?, ?, ?)";

        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, tradeProcessData.getAccountNo());
        insertStatement.setString(2, tradeProcessData.getCusip());
        insertStatement.setInt(3, quantity);
        insertStatement.setInt(4, 0);
        insertStatement.executeUpdate();
        connection.commit();
        updateJournalEntry(connection, tradeProcessData.getAccountNo(), tradeProcessData.getCusip());
    }

    public static void updatePosition(Connection connection, TradeProcessData tradeProcessData, int version) throws SQLException {
        connection.setAutoCommit(false);
        int quantity = tradeProcessData.getQuantity();
        String activity = tradeProcessData.getActivity();

        String updateQuery = "UPDATE positions SET position = position + ?, version = version + 1 WHERE account_number = ? AND security = ? AND version = ?";

        PreparedStatement updatedStatement = connection.prepareStatement(updateQuery);

        if (activity.equals("BUY")) {
            System.out.println("Called if block = " + activity);
            updatedStatement.setInt(1, quantity); // Add quantity
        } else {
            updatedStatement.setInt(1, -quantity); // Subtract quantity
        }

        updatedStatement.setString(2, tradeProcessData.getAccountNo());
        updatedStatement.setString(3, tradeProcessData.getCusip());
        updatedStatement.setInt(4, version);
        int rowsUpdated = updatedStatement.executeUpdate();
        connection.commit();
        if (rowsUpdated == 0) {
            connection.rollback();
            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
        }
        updateJournalEntry(connection, tradeProcessData.getAccountNo(), tradeProcessData.getCusip());
    }

    public static void upsertPosition(Connection connection, TradeProcessData tradeProcessData) throws SQLException {
        connection.setAutoCommit(false);
        String activity = tradeProcessData.getActivity();
        int quantity = tradeProcessData.getQuantity();
        String upsertQuery = "INSERT INTO positions (account_number, security, position) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE position = ";

        if (activity.equals("BUY")) {
            upsertQuery += "position + ?";
        } else {
            upsertQuery += "position - ?";
        }

        PreparedStatement upsertStatement = connection.prepareStatement(upsertQuery);
        upsertStatement.setString(1, tradeProcessData.getAccountNo());
        upsertStatement.setString(2, tradeProcessData.getCusip());
        upsertStatement.setInt(3, quantity);
        upsertStatement.setInt(4, quantity);
        upsertStatement.executeUpdate();

        connection.commit();
    }

    public static void updateJournalEntry(Connection connection, String accountNo, String cusip) throws SQLException {
        connection.setAutoCommit(false);
        String updateQuery = "UPDATE journal_entry SET posted_status = ? WHERE account_number = ? AND security = ?";


        PreparedStatement upsertStatement = connection.prepareStatement(updateQuery);
        upsertStatement.setString(1, "posted");
        upsertStatement.setString(2, accountNo);
        upsertStatement.setString(3, cusip);
        upsertStatement.executeUpdate();
        connection.commit();
    }
}
