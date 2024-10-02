package problems.tradingwiththreads.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.model.JournalEntryPOJO;
import problems.tradingwiththreads.model.PositionsPOJO;
import problems.tradingwiththreads.model.RawPayloadPOJO;
import problems.transactions.utility.OptimisticLockingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradesRepository {
    public void insertInRawTable(RawPayloadPOJO rawPayload, Connection connection) throws SQLException {
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
        System.out.println("done insertion into raw_payloads table tradeId:" + rawPayload.getTradeId());
    }


    public String getPayloadFromRawTable(String tradeId, Connection connection) throws SQLException {
        String queryForAccessingTradeId = "SELECT payload FROM trade_payloads_table WHERE trade_id = ?";
        String payload = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryForAccessingTradeId)) {
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                payload = resultSet.getString("payload");


            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            connection.close();
        }
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

    //CREATE TABLE journal_entry_table (
    //    journal_entry_id INT AUTO_INCREMENT PRIMARY KEY,
    //    account_number VARCHAR(20),
    //    cusip VARCHAR(9),
    //    direction ENUM('BUY', 'SELL'),
    //    quantity INT,
    //    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    //    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    //    FOREIGN KEY (cusip) REFERENCES securities_reference(cusip)

  //  public static void insertIntoJournalTable(JournalEntryPOJO journalEntry, Connection connection) {
  public static boolean insertIntoJournalTable(JournalEntryPOJO journalEntry, HikariDataSource dataSource){
        boolean inserted = false;
        String journalTableInsertion = "INSERT into journal_entry_table (account_number, cusip, direction, quantity) VALUES (?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatementjournal = connection.prepareStatement(journalTableInsertion);) {
            connection.setAutoCommit(false);

            if (lookupInSecuritiesTable(connection, journalEntry.getCusip())) {
                preparedStatementjournal.setString(1, journalEntry.getAccountNumber());
                preparedStatementjournal.setString(2, journalEntry.getCusip());
                preparedStatementjournal.setString(3, journalEntry.getDirection());
                preparedStatementjournal.setString(4, String.valueOf(journalEntry.getQuantity()));
                preparedStatementjournal.executeUpdate();
//                PositionsPOJO positionsInsertion = new PositionsPOJO();
//                insertIntoPositionsTable(positionsInsertion, connection);
                connection.commit();
                inserted = true;
                System.out.println("inserting in journal table");
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return inserted;
    }


//    CREATE TABLE positions (
//    position_id INT AUTO_INCREMENT PRIMARY KEY,
//    account_number VARCHAR(20) UNIQUE,
//    cusip VARCHAR(9),
//    position INT,
//    version INT,
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//    FOREIGN KEY (cusip) REFERENCES securities_reference(cusip)
//);
    public static void insertIntoPositionsTable(PositionsPOJO positionsPOJO, Connection connection) {
        String positionTableInsertion = "INSERT into positions (account_number, cusip, position, version) VALUES (?,?,?,0)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(positionTableInsertion)) {
            connection.setAutoCommit(false);

            preparedStatement.setString(1, positionsPOJO.getAccountNumber());
            preparedStatement.setString(2, positionsPOJO.getCusip());
            preparedStatement.setString(3, positionsPOJO.getQuantity());
            preparedStatement.execute();

            connection.commit();
            System.out.println("Inserting in the table from insertionQuery");
//                connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }




    public static boolean checkForPositions(Connection connection, PositionsPOJO positions) {
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


    public static void updatePositions(Connection connection, PositionsPOJO positions) throws SQLException {
        int quantity = Integer.parseInt(positions.getQuantity());
        String directions = positions.getPosition();
        String updateQuery = "UPDATE positions SET position = position + ?, version = version + 1 WHERE account_number = ? and cusip = ? and version = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            if(directions.equals("BUY")){
                System.out.println("Direction buy --- ");
                stmt.setInt(1, quantity);

            } else {
                directions.equals("SELL");
                    System.out.println("Direction sell ---");
                    stmt.setInt(1, -quantity);
                }

            stmt.setString(2, positions.getAccountNumber());
            stmt.setString(3, positions.getPosition());
            stmt.setInt(4, positions.getVersion());


            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                connection.rollback();
                throw new OptimisticLockingException("Optimistic locking failed");

            }
            connection.commit();
            System.out.println("Updated Query for positions from position table");
            connection.setAutoCommit(true);


        }

    }
}



//        private static void insertIntoPositionsTable(PositionsPOJO positionsPOJO, Connection connection) {
//            String positionTableInsertion = "INSERT into positions (account_number, cusip, position, version) VALUES (?,?,?,0)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(positionTableInsertion)) {
//                connection.setAutoCommit(false);
//
//                preparedStatement.setString(1, positionsPOJO.getAccountNumber());
//                preparedStatement.setString(2, positionsPOJO.getCusip());
//                preparedStatement.setString(3, positionsPOJO.getPosition());
//                preparedStatement.executeUpdate();
//
//                connection.commit();
//                System.out.println("updating table");
////                connection.rollback();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }

//    private static void updatePositions(Connection connection, PositionsPOJO positions) throws SQLException {
//
//        String updateQuery = "UPDATE positions SET position = ?, version = ? WHERE account_number = ? and cusip = ?" + "? and version = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
//            stmt.setString(1, positions.getAccountNumber());
//            stmt.setString(2, positions.getCusip());
//            stmt.setDouble(3, Double.parseDouble(positions.getPosition()));
//            stmt.setString(4, String.valueOf(positions.getVersion()));
//            int rowsUpdated = stmt.executeUpdate();
//
//            if(rowsUpdated == 0){
//                connection.rollback();
//                throw new OptimisticLockingException("Optimistic locking failed");
//
//            }
//            connection.commit();
//            connection.setAutoCommit(true);
//
//
//        }
//
//    }

