package trade_processing_multithreading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.concurrent.LinkedBlockingDeque;


public class TradeProcessor implements Runnable,QueProcessor {

    public LinkedBlockingDeque<String> que;
    public LinkedBlockingDeque<String> retryQue = new LinkedBlockingDeque<>();

    public TradeProcessor(LinkedBlockingDeque<String> que) {
        this.que = que;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String trade = que.take();
                processAndInsertToDB(trade);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void processAndInsertToDB(String trade) {
        String takeStatusFromRawDB = "select status from trade_payloads where trade_id = ?";
        String businessCheckQuery = "SELECT security_id FROM security_reference WHERE cusip = ?";
        String writeToJournalDB = "insert into journal_entry (account_number,security_id,direction,quantity) values(?,?,?,?)";
        String tradeId = null;

        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/src/trade_processing_multithreading/error_log/log.txt";
        try (Connection con = DatabaseConnector.getConnection()) {
            try (PreparedStatement businessCheckStat = con.prepareStatement(businessCheckQuery);
                 PreparedStatement takeStatusFromRawDBStat = con.prepareStatement(takeStatusFromRawDB);
                 PreparedStatement writeToJournalDBStat = con.prepareStatement(writeToJournalDB)) {
                con.setAutoCommit(false);
//                tradeId = que.take();
                takeStatusFromRawDBStat.setString(1, trade);
                ResultSet resultSet = takeStatusFromRawDBStat.executeQuery();
                resultSet.next();
                String status = resultSet.getString("status");
                String[] splitPayload = getTrade(con, trade);

                if (status.equals("Valid")) {
                    String CUSIP = splitPayload[3];
                    businessCheckStat.setString(1, CUSIP);
                    ResultSet businessCheckResult = businessCheckStat.executeQuery();
                    if (businessCheckResult.next()) {
                        int securityId = businessCheckResult.getInt("security_id");
                        writeToJournalDBStat.setString(1, splitPayload[2]);
                        writeToJournalDBStat.setInt(2, securityId);
                        writeToJournalDBStat.setString(3, splitPayload[4]);
                        writeToJournalDBStat.setInt(4, Integer.parseInt(splitPayload[5]));
                        Double.parseDouble(splitPayload[6]);
                        writeToJournalDBStat.executeUpdate();
                        writeToPositionsDB(con, trade);
                    } else {
                        LogFileWriter.writeLog("Failed Business Check" + " for Trade Id -> " + tradeId + " CUSIP -> " + CUSIP, filepath);
                    }
                } else {
                    LogFileWriter.writeLog("Invalid Status because of Missing Fields", filepath);
                }
                con.commit();
            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                que.putFirst(trade);
                con.rollback();
            } catch (DateTimeParseException | NumberFormatException e) {
                LogFileWriter.writeLog(e.getMessage() + " -> " + trade, filepath);
            } catch (SQLException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getTrade(Connection connection, String tradeId) {
        String takePayloadFromRawDB = "select payload,status from trade_payloads where trade_id = ?";
        String[] splitPayload;
        try (PreparedStatement takePayloadFromRawDBStat = connection.prepareStatement(takePayloadFromRawDB)) {
            takePayloadFromRawDBStat.setString(1, tradeId);
            ResultSet resultSet = takePayloadFromRawDBStat.executeQuery();
            resultSet.next();
            String payload = resultSet.getString("payload");
            splitPayload = payload.split(",");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return splitPayload;
    }

    public boolean writeToPositionsDB(Connection connection, String tradeId) {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
            String[] splitPayload = getTrade(connection, tradeId);
            String CUSIP = splitPayload[3];
            String accountNumber = splitPayload[2];
            try {
                int version = getTradeVersion(connection, accountNumber, CUSIP);
                if (version == -1) {
                    insertTradeAccount(connection, splitPayload);
                } else {
                    updateTrade(connection, splitPayload, version);
                }
//                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for trade_id: " + tradeId);
            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
//                que.putFirst(tradeId);
                connection.rollback();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private int getTradeVersion(Connection connection, String accountNumber, String CUSIP) throws SQLException {
        String versionQuery = "select version from positions WHERE account_number = ? And security_id = ?";
        try (PreparedStatement versionQueryStat = connection.prepareStatement(versionQuery)) {
            versionQueryStat.setString(1, accountNumber);
            int securityID = getSecurityID(connection, CUSIP);
            versionQueryStat.setInt(2, securityID);
            ResultSet rs = versionQueryStat.executeQuery();
            if (rs.next()) {
                return rs.getInt("version");
            } else {
                return -1;
            }
        }
    }

    private int getSecurityID(Connection connection, String CUSIP) throws SQLException {
        String businessCheckQuery = "select security_id from security_reference where cusip = ?";
        try (PreparedStatement businessCheckQueryStat = connection.prepareStatement(businessCheckQuery)) {
            businessCheckQueryStat.setString(1, CUSIP);
            ResultSet businessQueryResultSet = businessCheckQueryStat.executeQuery();
            businessQueryResultSet.next();
            int securityId = businessQueryResultSet.getInt("security_id");
            return securityId;
        }
    }

    private void insertTradeAccount(Connection connection, String[] splitPayload) throws SQLException {
        connection.setAutoCommit(false);
        String insertQuery = "insert into positions (account_number,security_id,position,version) value (?,?,?,0)";
        try (PreparedStatement insertQueryStat = connection.prepareStatement(insertQuery)) {
            int securityId = getSecurityID(connection, splitPayload[3]);
            insertQueryStat.setString(1, splitPayload[2]);
            insertQueryStat.setInt(2, securityId);
            insertQueryStat.setInt(3, Integer.parseInt(splitPayload[5]));
            insertQueryStat.executeUpdate();
            System.out.println("Inserted new Trade for card: " + splitPayload[0]);
            connection.commit();
        }
    }

    private void updateTrade(Connection connection, String[] splitPayload, int version) throws SQLException {
        String positionQuery = "select position from positions where account_number=? and security_id = ?";
        String updateQuery = "UPDATE positions SET position = ?, version = version + 1 where account_number = ? AND version = ? AND security_id = ?";
        try (PreparedStatement positionQueryStat = connection.prepareStatement(positionQuery);
             PreparedStatement updateQueryStat = connection.prepareStatement(updateQuery)) {
            connection.setAutoCommit(false);
            int securityID = getSecurityID(connection, splitPayload[3]);
            positionQueryStat.setString(1, splitPayload[2]);
            positionQueryStat.setInt(2, securityID);
            ResultSet positionQueryResultSet = positionQueryStat.executeQuery();
            positionQueryResultSet.next();
            int position = positionQueryResultSet.getInt("position");
            if (splitPayload[4].equals("SELL")) {
                updateQueryStat.setInt(1, Integer.parseInt(splitPayload[5]) - position);
            } else {
                updateQueryStat.setInt(1, Integer.parseInt(splitPayload[5]) + position);
            }
            updateQueryStat.setString(2, splitPayload[2]);
            updateQueryStat.setInt(3, version);
            updateQueryStat.setInt(4, securityID);
            int rowsUpdated = updateQueryStat.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
            }
        }
        connection.commit();
//        connection.setAutoCommit(true);
    }
}
