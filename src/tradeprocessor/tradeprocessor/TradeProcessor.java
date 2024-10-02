package tradeprocessor.tradeprocessor;

import tradeprocessor.dbconnection.TradingDatabseConnectionPool;
import tradeprocessor.exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;


public class TradeProcessor {

    public TradeProcessor(LinkedBlockingDeque<String> tradeProcessorQueue) {
        System.out.println("reaching inside tradeProcessor");
        try {
            processQueue(tradeProcessorQueue);

        } catch (Exception e) {
            throw new TradeProcessingException("Error processing tradeQueue",e);
        }
    }

    public void processQueue(LinkedBlockingDeque<String> tradeProcessorQueue) throws Exception {
        System.out.println("reached inside processQueue");
        while (!tradeProcessorQueue.isEmpty()) {
            String tradeId = tradeProcessorQueue.take();
            getPayload(tradeId);
        }
    }

    public void getPayload(String tradeId) throws Exception {

        System.out.println("reached inside getPayload");
        String payloadQuery = "SELECT payload FROM trade_payload WHERE trade_id = ? AND status = 'VALID'";
        try (Connection connection = TradingDatabseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(payloadQuery)) {

            preparedStatement.setString(1, tradeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String lines = rs.getString("payload");
                if (lookupSecurityTable(lines)) {
                    insertIntoJournalTable(lines);

                }

            }
        }
    }

    public boolean lookupSecurityTable(String lines) throws Exception {
        System.out.println("reached inside lookupSecurityTable");
        String[] columns = lines.split(",");
        String securityLookupQuery = "SELECT cusip FROM SecuritiesReference WHERE cusip = ?";
        try (Connection connection = TradingDatabseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(securityLookupQuery)) {

            preparedStatement.setString(1, columns[3]);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }

    }


    public void insertIntoJournalTable(String lines) throws JournalEntryException {
        System.out.println("reached inside insertion Journal");
        String journalQuery = "INSERT INTO journal_entry (account_id, direction, quantity, cusip) VALUES(?,?,?,?) ";
        String[] columns = lines.split(",");
        String accountId = columns[2];
        try (Connection connection = TradingDatabseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(journalQuery)) {

            preparedStatement.setString(1, accountId);
            preparedStatement.setString(2, columns[4]);
            preparedStatement.setInt(3, Integer.parseInt(columns[5]));
            preparedStatement.setString(4, columns[3]);
            preparedStatement.executeUpdate();

            processTraditionPositions(columns[2], columns[3], columns[4], Integer.parseInt(columns[5]));

        } catch (SQLException e) {
            throw new JournalEntryException("Failed to insert into journal table",e);
        } catch (Exception e) {
            throw new JournalEntryException("Failed to process journal entry",e);
        }
    }

    public void processTraditionPositions(String accountId, String cusip, String direction, int quantity) {
        try (Connection connection = TradingDatabseConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            processTransaction(connection, accountId, cusip, direction, quantity);
            connection.commit();
        } catch (SQLException e) {
            throw new PositionProcessingException("Database error while processing trade position", e);
        }
    }

    private void processTransaction(Connection connection, String accountId, String cusip, String direction, int quantity) {
        try {
            int version = getAccountVersion(connection, accountId, cusip);

            if (version == -1) {
                insertIntoPosition(connection, accountId, cusip, quantity);
            } else {
                updatePosition(connection, accountId, cusip, direction, quantity, version);
            }
        } catch (Exception e) {
            throw new PositionProcessingException("Failed to process trade position", e);
        }
    }

    private void updatePosition(Connection connection, String accountId, String cusip, String direction, int quantity, int version) throws Exception {
        int position;
        String selectQuery = "SELECT position,version FROM position WHERE account_id = ? AND cusip = ?";
        String updateQuery = "UPDATE position SET position = ? and version = ? WHERE account_id = ? AND cusip =?";
        try (PreparedStatement selectstmt = connection.prepareStatement(selectQuery);
             PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            selectstmt.setString(1, accountId);
            selectstmt.setString(2, cusip);
            ResultSet rs = selectstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Reached updatePosition : " + accountId + " , cusip : " + cusip + " , ver : " + version + " , quantity : " + quantity);

                position = rs.getInt("position");
                System.out.println("position :"+position);
                System.out.println("version :"+rs.getInt("version"));

                if (direction.equals("BUY")) {
                    stmt.setInt(1, position + quantity);
                } else if (direction.equals("SELL")) {
                    stmt.setInt(1, position - quantity);
                }
                stmt.setInt(2, version + 1);
                stmt.setString(3, accountId);
                stmt.setString(4, cusip);


                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated == 0) {
                    System.out.println("Update position :  exception");
                    connection.rollback();
                    throw new OptimisticLockingException("Optimistic locking failed,retrying transaction....");
                }
            }
        }
    }

    private void insertIntoPosition(Connection connection, String accountId, String cusip, int quantity) throws PositionInsertionException {
        System.out.println("Reached insertIntoPosition");
        int position = 0;
        String insertQuery = "INSERT INTO position (account_id, cusip, position) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setString(1, accountId);
            stmt.setString(2, cusip);
            stmt.setInt(3, position + quantity);
            stmt.executeUpdate();
            System.out.println("Inserted new position for account_id " + accountId);

        }catch (SQLException e) {
            throw new PositionInsertionException("Failed to insert into position for account_id " + accountId, e);
        }catch (Exception e) {
            throw new PositionInsertionException("Unexpected error while inserting into position",e);
        }
    }

    public int getAccountVersion(Connection connection, String accountId, String cusip) throws AccountVersionException {
        System.out.println("Reached getAccountVersion");
        String query = "SELECT version FROM position WHERE account_id = ? AND cusip =?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountId);
            stmt.setString(2, cusip);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("version");
            } else {
                return -1;
            }
        }catch (SQLException e){
            throw new AccountVersionException("Failed to retrieve account version for accountId: " + accountId + " and cusip: " + cusip, e);
        }catch (Exception e) {
            throw new AccountVersionException("Unexpected error while retrieving account version", e);
        }
    }


}



