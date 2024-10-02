package problems.tradingwiththreads.repository;

import problems.tradingwiththreads.model.JournalEntryPOJO;
import problems.tradingwiththreads.model.PositionsPOJO;
import problems.tradingwiththreads.model.RawPayloadPOJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradesRepository {
    public void insertInRawTable(RawPayloadPOJO rawPayload, Connection connection) {
        String rawTableQuery = "INSERT into trade_payloads_table(trade_id, status, status_reason, payload) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawTableQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, rawPayload.getTradeId());
            preparedStatement.setString(2, rawPayload.getStatus());
            preparedStatement.setString(3, rawPayload.getStatusReason());
            preparedStatement.setString(4, rawPayload.getPayload());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getPayloadFromRawTable(String tradeId, Connection connection){
        String queryForAccessingTradeId = "SELECT payload FROM trade_payloads_table WHERE trade_id = ?";
        String payload = "";

        try(PreparedStatement preparedStatement = connection.prepareStatement(queryForAccessingTradeId)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               payload = resultSet.getString("payload");


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }


    public static boolean lookupInSecuritiesTable(Connection connection, String cusip){
        String lookupQuery = "SELECT 1 from securities_reference WHERE cusip = ?";
        try (PreparedStatement stmt = connection.prepareStatement(lookupQuery)) {

            stmt.setString(1, cusip);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return false;
        }

    }

    public static void insertIntoJournalTable(JournalEntryPOJO journalEntry, Connection connection){
        String journalTableInsertion = "INSERT into journal_entry_table (account_number, cusip, direction, quantity) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(journalTableInsertion)) {
            connection.setAutoCommit(false);

            if(lookupInSecuritiesTable(connection, journalEntry.getCusip())){
                preparedStatement.setString(1, journalEntry.getAccountNumber());
                preparedStatement.setString(2, journalEntry.getCusip());
                preparedStatement.setString(3, journalEntry.getDirection());
                preparedStatement.setString(4, journalEntry.getQuantity());

//            preparedStatement.setString(1, journalEntry.getTradeId());
//            preparedStatement.setTimestamp(2, journalEntry.getTransactionTime());
//            preparedStatement.setString(7, journalEntry.getPostedStatus());

                preparedStatement.executeUpdate();
                PositionsPOJO positions = new PositionsPOJO();
                insertIntoPositionsTable(positions, connection);
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void insertIntoPositionsTable(PositionsPOJO positionsPOJO, Connection connection){
        String positionTableInsertion = "INSERT into positions (account_number, cusip, position) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(positionTableInsertion)) {
            connection.setAutoCommit(false);

                preparedStatement.setString(1, positionsPOJO.getAccountNumber());
                preparedStatement.setString(2, positionsPOJO.getCusip());
                preparedStatement.setString(3, positionsPOJO.getPosition());
                preparedStatement.executeUpdate();
                connection.commit();
//                connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
