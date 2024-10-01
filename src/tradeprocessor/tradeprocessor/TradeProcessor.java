package tradeprocessor.tradeprocessor;

import shapeless.record;
import tradeprocessor.dbconnection.TradingDatabseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;


public class TradeProcessor {


    private String lines;
    String cusip;

    LinkedBlockingDeque<String> tradeProcessorQueue;


    public TradeProcessor(LinkedBlockingDeque<String> tradeProcessorQueue) {
        System.out.println("reaching inside tradeProcessor");
        try {
            processQueue(tradeProcessorQueue);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void processQueue(LinkedBlockingDeque<String> tradeProcessorQueue) throws Exception {
        int processQueueCount = 0;
        System.out.println("reached inside processQueue");
        while (!tradeProcessorQueue.isEmpty()) {
            processQueueCount++;
            String tradeId = tradeProcessorQueue.take();
            getPayload(tradeId);
        }
        System.out.println("+====================!!!!!!!!!!!!!!!!!!!!!!!!!" + processQueueCount);
    }

    public void getPayload(String tradeId) throws Exception {

        System.out.println("reached inside getPayload");
        String payloadQuerry = "SELECT payload FROM trade_payload WHERE trade_id = ? AND status = 'VALID'";
        try (Connection connection = TradingDatabseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(payloadQuerry)) {

            preparedStatement.setString(1, tradeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                lines = rs.getString("payload");
                System.out.println("line retrived = " + lines);

                if (lookupSecurityTable(lines)) {
                    isertIntoJournalTable(lines);

                }

            }
            String[] strarray = lines.split(",");
            System.out.println("ACCOUNT ID => " + strarray[2]);

        }
    }

    public boolean lookupSecurityTable(String lines) throws Exception {
        System.out.println("reached inside lookupSecurityTable");
        String[] columns = lines.split(",");
        String securityLookupQuerry = "SELECT cusip FROM SecuritiesReference WHERE cusip = ?";
        try (Connection connection = TradingDatabseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(securityLookupQuerry)) {

            preparedStatement.setString(1, columns[3]);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
//                cusip = rs.getString("cusip"));
                return true;
            } else {
                return false;
            }
        }

    }


    public void isertIntoJournalTable(String lines) {
        System.out.println("reached inside insertinto Journal");
        String journalQuerry = "INSERT INTO journal_entry (account_id, direction, quantity, cusip) VALUES(?,?,?,?) ";
//                "SELECT ?, ?, ?, sr.cusip " +
//                "FROM SecuritiesReference sr " +
//                "WHERE sr.cusip = ?";
        String[] columns = lines.split(",");
        String account_id = columns[2];
        try (Connection connection = TradingDatabseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(journalQuerry)) {

            preparedStatement.setString(1, account_id);
            preparedStatement.setString(2, columns[4]);
            preparedStatement.setInt(3, Integer.parseInt(columns[5]));
            preparedStatement.setString(4, columns[3]);
            preparedStatement.executeUpdate();

            processTradeintoPositions(columns[2], columns[3], columns[4], Integer.parseInt(columns[5]));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void processTradeintoPositions(String accountId, String cusip, String direction, int quantity) throws Exception {
        try (Connection connection = TradingDatabseConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                int verison = getAccountVersion(connection, accountId,cusip);

                if (verison == -1) {
                    insertIntoPosition(connection, accountId, cusip, quantity);
                } else {

                    //TODO - get position here as version and do operations BUY/SELL

                    updatePosition(connection, accountId, cusip, direction, quantity, verison);

                }
                connection.commit();
            } catch (Exception e) {
                System.out.println("Exception during position adding : " + e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println("Exception during position adding : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void updatePosition(Connection connection, String accountId, String cusip, String direction, int quantity, int version) throws Exception {
        int position;
        String selectQuerry = "SELECT position,version FROM position WHERE account_id = ? AND cusip = ?";
        String updateQuerry = "UPDATE position SET position = ? and version = ? WHERE account_id = ? AND cusip =?";
        try (PreparedStatement selectstmt = connection.prepareStatement(selectQuerry);
             PreparedStatement stmt = connection.prepareStatement(updateQuerry)) {
            selectstmt.setString(1, accountId);
            selectstmt.setString(2, cusip);
            ResultSet rs = selectstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Reached updatePosition : " + accountId + " , cusip : " + cusip + " , ver : " + version + " , quantitty : " + quantity);

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
                    System.out.println("Update position :  exeception");
                    connection.rollback();
                    throw new OptimisticLockingException("Optimistic locking failed,retrying transactin....");
                }
            }
        }
    }

    private void insertIntoPosition(Connection connection, String account_id, String cusip, int quantity) throws Exception {
        System.out.println("Reached insertIntoPosition");
        Integer position = 0;
        String insertQuery = "INSERT INTO position (account_id, cusip, position) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setString(1, account_id);
            stmt.setString(2, cusip);
            stmt.setInt(3, position + quantity);
            stmt.executeUpdate();
            System.out.println("Inserted new position for account_id " + account_id);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getAccountVersion(Connection connection, String account_Id, String cusip) throws Exception {
        System.out.println("Reached getAccountVersion");
        String query = "SELECT version FROM position WHERE account_id = ? AND cusip =?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, account_Id);
            stmt.setString(2, cusip);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("version");
            } else {
                return -1;
            }
        }
    }

    class OptimisticLockingException extends RuntimeException {
        public OptimisticLockingException(String message) {
            super(message);
        }
    }
}



