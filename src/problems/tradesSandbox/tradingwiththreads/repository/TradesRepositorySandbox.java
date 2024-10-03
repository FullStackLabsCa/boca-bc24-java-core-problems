package problems.tradesSandbox.tradingwiththreads.repository;

import com.zaxxer.hikari.HikariDataSource;
//import problems.tradesSandbox.tradingwiththreads.model.JournalEntry;
//import problems.tradesSandbox.tradingwiththreads.model.Positions;
import problems.tradesSandbox.tradingwiththreads.model.PositionsSandbox;
import problems.tradesSandbox.tradingwiththreads.model.RawPayloadSandbox;
//import problems.tradesSandbox.transactions.utility.OptimisticLockingException;
import problems.tradesSandbox.tradingwiththreads.model.JournalEntrySandbox;
import problems.tradingwiththreads.model.JournalEntry;
import problems.tradingwiththreads.model.Positions;
import problems.transactions.utility.OptimisticLockingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static problems.tradesSandbox.tradingwiththreads.TradeThreadMain.dataSource;

public class TradesRepositorySandbox {
    public void insertInRawTable(RawPayloadSandbox rawPayload, HikariDataSource dataSource) throws SQLException {
        String rawTableQuery = "INSERT into trade_payloads_table(trade_id, status, status_reason, payload) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(rawTableQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, rawPayload.getTradeId());
            preparedStatement.setString(2, rawPayload.getStatus());
            preparedStatement.setString(3, rawPayload.getStatusReason());
            preparedStatement.setString(4, rawPayload.getPayload());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("done insertion into raw_payloads table tradeId:" + rawPayload.getTradeId());
    }


    public String getPayloadFromRawTableSandbox(String tradeId, Connection connection) throws SQLException {
        String queryForAccessingTradeId = "SELECT payload FROM trade_payloads_table WHERE trade_id = ?";
        String payload = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryForAccessingTradeId)) {
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payload = resultSet.getString("payload");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println(" PAYLOAD" + payload);
        return payload;
    }


    public static boolean lookupInSecuritiesTable(Connection connection, String cusip) {
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
            e.printStackTrace();
            return false;
        }
    }

    public void insertIntoJournalTable(JournalEntrySandbox journalEntry, Connection connection) {
        String journalTableInsertion = "INSERT into journal_entry_table (account_number, cusip, direction, quantity) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatementjournal = connection.prepareStatement(journalTableInsertion);) {

            System.out.println("lookupInSecuritiesTable(connection, journalEntry.getJournalCusip()) = " + lookupInSecuritiesTable(connection, journalEntry.getJournalCusip()));
            if (lookupInSecuritiesTable(connection, journalEntry.getJournalCusip())) {
                preparedStatementjournal.setString(1, journalEntry.getJournalAccountID());
                preparedStatementjournal.setString(2, journalEntry.getJournalCusip());
                preparedStatementjournal.setString(3, journalEntry.getJournalDirection());
                preparedStatementjournal.setString(4, String.valueOf(journalEntry.getJournalQuantity()));
                preparedStatementjournal.executeUpdate();
                PositionsSandbox positionsInsertion = new PositionsSandbox();
                positionsInsertion.setPositionAccountId(journalEntry.getJournalAccountID());
                positionsInsertion.setPositionCusip(journalEntry.getJournalCusip());
                positionsInsertion.setPositionPosition(String.valueOf(journalEntry.getJournalQuantity()));
                //calling position table
                //cannot do this call in a nested way
                //insertIntoPositionsTable(positionsInsertion, connection);

                System.out.println("Row inserted successfully in database.");
//                System.out.println("inserting in journal table");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    public boolean getPosition(Connection connection, Positions positions) {
        String query = "SELECT position_id FROM positions WHERE account_number = ? and cusip = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, positions.getAccountNumber());
            stmt.setString(2, positions.getCusip());
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void doUpdateOrInsert(Connection connection, PositionsSandbox positionsSandbox, JournalEntrySandbox journalEntrySandbox) throws SQLException {
        while (true)
            if (getVersion(connection, positionsSandbox) == -1) {
                insertIntoPositionsTable(positionsSandbox, connection);
            } else {
                updatePosition(connection, positionsSandbox, journalEntrySandbox);
            }
    }

    public int getVersion(Connection connection, PositionsSandbox positionsSandbox) throws SQLException {
        String versionQuery = "SELECT version from positions where account_number = ? and cusip = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(versionQuery)) {
            preparedStatement.setString(1, positionsSandbox.getPositionAccountId());
            preparedStatement.setString(2, positionsSandbox.getPositionCusip());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("version");
            } else {
                return -1;
            }
        }
    }

    public void insertIntoPositionsTable(PositionsSandbox positionsPOJO, Connection connection) throws SQLException {
        String positionTableInsertion = "INSERT into positions (account_number, cusip, position, version) VALUES (?,?,?,0)";
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(positionTableInsertion)) {
            if (lookupInSecuritiesTable(connection, positionsPOJO.getPositionCusip())) {
                preparedStatement.setString(1, positionsPOJO.getPositionAccountId());
                preparedStatement.setString(2, positionsPOJO.getPositionCusip());
                preparedStatement.setInt(3, Integer.parseInt(positionsPOJO.getPositionPosition()));
                preparedStatement.executeUpdate();
                connection.commit();
                System.out.println("Inserting in the table from insertionQuery");
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updatePosition(Connection connection, PositionsSandbox positionsSandbox, JournalEntrySandbox journalEntrySandbox) throws SQLException {
        connection.setAutoCommit(false);
        String updateQuery;

        if (journalEntrySandbox.getJournalDirection().equals("BUY")) {
            updateQuery = "UPDATE positions SET position = position + ?, version = version + 1 where account_number = ? and cusip = ? and version = ?";
        } else {
            updateQuery = "UPDATE positions SET position = position - ?, version = version + 1 where account_number = ? and cusip = ? and version = ?";
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, positionsSandbox.getPositionAccountId());
            preparedStatement.setString(2, positionsSandbox.getPositionCusip());
            preparedStatement.setString(3, positionsSandbox.getPositionPosition());
            preparedStatement.setInt(4, positionsSandbox.getPositionVersion());
            int rowsUpdate = preparedStatement.executeUpdate();
            if (rowsUpdate == 0) {
                connection.rollback();
                throw new OptimisticLockingException("Optimistic blocking failed");
            }
            connection.commit();
            System.out.println("Updated Query for positions from position table");
            connection.setAutoCommit(true);
        }
    }


}




