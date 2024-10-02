package multithreadingtrade.repo;

import multithreadingtrade.databaseconnectivity.DatabaseConnection;
import multithreadingtrade.trademodel.JournalEntry;
import multithreadingtrade.trademodel.RawPayLoad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeRepo {
    public void insertIntoRawPayLoad(RawPayLoad rawPayLoad) {
        String query = "Insert into trade_payloads (trade_id, status, status_reason, payload) values(?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rawPayLoad.getTradeID());
            preparedStatement.setString(2, rawPayLoad.getStatus());
            preparedStatement.setString(3, rawPayLoad.getStatusReason());
            preparedStatement.setString(4, rawPayLoad.getPayLoads());
            preparedStatement.execute();
            System.out.println("File inserted successfully for :" + rawPayLoad.getTradeID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String readPayloadFromRawTable(String tradeId) throws SQLException {
        String query = "Select * from trade_payloads where trade_id = ?";
        String payload = "";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payload = resultSet.getString("payload");
                System.out.println("payload = " + payload);
            }
            return payload;
        }
    }

    public static boolean lookUpInSecuritiesTable(String cusip, Connection connection) throws SQLException {
        boolean validateSecurity = false;
        String query = "Select 1 from securities_reference where cusip = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cusip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
              return true;
            }
            return validateSecurity;

        }
    }

    public static void insertIntoJournalTable(JournalEntry journalEntry) {
        String journalTableInsertion = "INSERT into journal_entries (account_number, cusip, direction, quantity,posted_status) VALUES (?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(journalTableInsertion)) {
            //connection.setAutoCommit(false);

            if (lookUpInSecuritiesTable(journalEntry.getCusip(), connection)) {
                preparedStatement.setString(1, journalEntry.getAccountNumber());
                preparedStatement.setString(2, journalEntry.getCusip());
                preparedStatement.setString(3, journalEntry.getDirection());
                preparedStatement.setString(4, String.valueOf(journalEntry.getQuantity()));
                preparedStatement.setString(5, journalEntry.getPostedStatus());
                preparedStatement.executeUpdate();
//                connection.commit();
            } else {
//                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToPositionTable() {

    }
}
