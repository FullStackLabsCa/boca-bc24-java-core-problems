package multithreadingtrade.repo;

import multithreadingtrade.databaseconnectivity.DBUtils;
import multithreadingtrade.trademodel.JournalEntry;
import multithreadingtrade.trademodel.Positions;
import multithreadingtrade.trademodel.RawPayLoad;

import java.sql.*;
//TODO: NEED TO FIX IT RENAME THE CLASS

public class TradeRepo {
    final static String INSERT_STMT_JRNL_ENTRIES = "INSERT into journal_entries (account_number, cusip, direction, quantity, posted_status) VALUES (?,?,?,?,?)";
    final static String INSERT_STMT_TRADE = "Insert into trade_payloads (trade_id, status, status_reason, payload) values(?, ?, ?, ?)";
    private DBUtils dbUtils;

    public TradeRepo() {
        this.dbUtils = DBUtils.getInstance();
    }

    // Insert raw payload
    public void insertIntoRawPayLoad(RawPayLoad rawPayLoad)  {
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STMT_TRADE)) {
            preparedStatement.setString(1, rawPayLoad.getTradeID());
            preparedStatement.setString(2, rawPayLoad.getStatus());
            preparedStatement.setString(3, rawPayLoad.getStatusReason());
            preparedStatement.setString(4, rawPayLoad.getPayLoads());
            preparedStatement.execute();
            System.out.println("trade inserted successfully for tradeId :"+rawPayLoad.getTradeID());
//            System.out.println("File inserted successfully for: " + rawPayLoad.getTradeID());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
//            System.out.println("Error inserting raw payload: " + e.getMessage());
        }
    }

    // Read payload from raw table
    public String readPayloadFromRawTable(String tradeId) throws SQLException {
        String query = "Select payload from trade_payloads where trade_id = ?";
        String payload = "";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    payload = resultSet.getString("payload");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
         //   System.out.println("PAYLOAD" + payload);
            return payload;
        }
    }
    // Lookup CUSIP in securities table
    public boolean lookUpInSecuritiesTable(String securityCusip) {
        String query = "Select 1 from securities_reference where cusip = ?";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, securityCusip);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Insert into journal table
    public void insertIntoJournalTable(JournalEntry journalEntry) {

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STMT_JRNL_ENTRIES)) {


            // Insert into journal table
            preparedStatement.setString(1, journalEntry.getAccountNumber());
            preparedStatement.setString(2, journalEntry.getCusip());
            preparedStatement.setString(3, journalEntry.getDirection());
            preparedStatement.setString(4, String.valueOf(journalEntry.getQuantity()));
            preparedStatement.setString(5, journalEntry.getPostedStatus());
            preparedStatement.executeUpdate();

       //     System.out.println("Journal entry inserted for trade: " + journalEntry.getTradeId());

        } catch (SQLException e) {


            // Handle SQL exceptions
            System.out.println("SQL exception for trade: " + journalEntry.getTradeId() + " - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);

        } catch (RuntimeException e) {
            System.out.println("Journal entry invalid for trade: " + journalEntry.getTradeId() + " - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    // Additional methods for positions (get and update)
    public int getPositionVersion(Positions positions) throws SQLException {
        String versionQuery = "Select version from positions where account_number = ? AND cusip = ?";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(versionQuery)) {
            preparedStatement.setString(1, positions.getAccountNumber());
            preparedStatement.setString(2, positions.getCusip());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("version");
                } else {
                    return -1;
                }
            }
        }
    }

    public int getPosition(Positions positions) throws SQLException {
        String positionQuery = "Select position from positions where account_number = ? AND cusip = ?";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(positionQuery)) {
            preparedStatement.setString(1, positions.getAccountNumber());
            preparedStatement.setString(2, positions.getCusip());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("position");
                } else {
                    return 0;
                }
            }
        }
    }

    public void writeToPositionTable(Positions positions) throws SQLException {
        String positionTableInsertion = "INSERT into positions (account_number, cusip, quantity, version, position) VALUES (?,?,?,0,?)";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(positionTableInsertion)) {
            preparedStatement.setString(1, positions.getAccountNumber());
            preparedStatement.setString(2, positions.getCusip());
            preparedStatement.setInt(3, positions.getQuantity());
            preparedStatement.setString(4, positions.getPosition());
            preparedStatement.executeUpdate();
        }
    }

    public void updatePositionTable(Positions positions) throws SQLException {
        String updateQuery = "UPDATE positions SET position = ?, version = version + 1 WHERE account_number = ? AND cusip = ? AND version = ?";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            int currentPosition = getPosition(positions);
            int newQuantity = positions.getQuantity();
            if (positions.getPosition().equals("BUY")) {
                preparedStatement.setInt(1, currentPosition + newQuantity);
            } else if (positions.getPosition().equals("SELL")) {
                preparedStatement.setInt(1, currentPosition - newQuantity);
            }
            preparedStatement.setString(2, positions.getAccountNumber());
            preparedStatement.setString(3, positions.getCusip());
            preparedStatement.setInt(4, positions.getVersion());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();  // If no rows were updated, rollback
            }
        }
    }
}