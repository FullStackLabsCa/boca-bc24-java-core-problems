package problems.tradeProcessing.trades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tradeProcessing.customeinterface.files.*;
import problems.tradeoperations.manager.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessor implements TradeProcessorInterface, Runnable, TradePayloadServiceInterface, SecuritiesReferenceService, JournalEntryServiceInterface, TradePositionInterface {

    private static final Logger log = LoggerFactory.getLogger(TradeProcessor.class);
    private LinkedBlockingDeque<String>[] tradeQueues;
    private Connection connection;
    private Random random = new Random();
    private int journalEntryCount;
//    Connection connection = DatabaseConnection.getConnection();

    public TradeProcessor(LinkedBlockingDeque<String>[] tradeQueues, Connection connection) {
        this.tradeQueues = tradeQueues;
        this.connection = connection;
    }

    // Runnable Interface - by default use for thread
    @Override
    public void run() {
        try {
            while (true){
                int randomQueueIndex = random.nextInt(3);
                String tradeId = tradeQueues[randomQueueIndex].poll();

                if(tradeId != null){
                    processTrade(tradeId);
                } else {
                    Thread.sleep(1000); // sleep if trade was not found, avoidd busy waiting
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Total journal entries inserted: "  + journalEntryCount);
        }
    }

    // TradeProcessorInterface
    @Override
    public void processTrade(String tradeId) throws Exception {
        String status = fetchTradeStatus(tradeId);
        if("valid".equals(status)){
            // 1. fetch payload
            String payload = fetchTradePayload(tradeId);
            if(payload != null){
                // 2. parse payload - extarct fields - use cusip
                String[] fields = parsePayload(payload);
                String cusip = fields[3]; // cusip - 3rd position
                // 3. find security id - using cusip
                String securityId = getSecurityIdByCusip(cusip);
                if (securityId != null){
                    // 4. insert into journal_entry
                    insertJournalEntry(fields, securityId); // called for journal entry table
                    // After insert into journal entry taable update the position table
                    String accountId = fields[2];
                    String direction = fields[4];
                    int quantity = Integer.parseInt(fields[5]);
                    upsertPosition(accountId, securityId, quantity, direction); // called for position table
                } else {
                    System.out.println("CUSIP not found" + cusip);
                }
            }
        } else {
            System.out.println("Trade id '" + tradeId + "' can not be proceed further due to status is '" + status +"'");
        }

    }

    private String[] parsePayload(String payload) {
        return  payload.split(",");
    }


    private String fetchTradeStatus(String tradeId) throws SQLException{
        String status = null;
        // prepare & execute query
        String query = "SELECT status FROM trade_payloads WHERE trade_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, tradeId);
            ResultSet rs = stmt.executeQuery();
            // retrive status
            if (rs.next()){
                status = rs.getString("status");
            }
        }
        return status;
    }

    @Override
    public String fetchTradePayload(String tradeId) throws SQLException {
        // prepare & execute query
        String query = "SELECT payload FROM trade_payloads WHERE trade_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, tradeId);
            ResultSet rs = stmt.executeQuery();
            // retrive status
            if (rs.next()){
                return rs.getString("payload");
            }
        }
        return null;
    }

    @Override
    public String getSecurityIdByCusip(String cusip) throws SQLException {
        String query = "SELECT security_id FROM securitiesReference WHERE cusip = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cusip);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("security_id");
            }
        }
        return null;
    }

    @Override
    public void insertJournalEntry(String[] fields, String securityId) throws SQLException{
        String insertQuery = "INSERT INTO journal_entry (account_id, security_id, direction, quantity, posted_status) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setString(1, fields[2]); // account number - parsed data
            stmt.setString(2, securityId); // security ID - securityReference table
            stmt.setString(3, fields[4]); // direction/activity - parsed data
            stmt.setString(4, fields[5]); // quantity - parsed data
            stmt.setString(5, "posted");  // default posted status
            stmt.executeUpdate();
            journalEntryCount++;
            System.out.println("Journal entry inserted for trade ID: " + fields[0]);
        }

    }

    @Override
    public void upsertPosition(String accountId, String securityId, int quantity, String direction) throws SQLException {

        boolean updated = false;

        while(!updated){

            // fetch current position & version
            String query = "SELECT position, version FROM positions WHERE account_id = ? AND security_id = ?";
            int currentPosition = 0;
            int currentVersion =0;
            boolean positionExists = false;

            try (PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1, accountId);
                stmt.setString(2, securityId);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {
                    currentPosition = rs.getInt("position");
                    currentVersion = rs.getInt("version");
                    positionExists = true;
                }
            }

            // calculate new position
            int newPosition = currentPosition;

            if("buy".equalsIgnoreCase(direction)){
                newPosition += quantity;
            } else if ("sell".equalsIgnoreCase(direction)) {
                newPosition -= quantity;
            }

            if (positionExists) {
                // 3. update - optimistik locking
                String upadateQuery = "UPDATE positions SET position = ?, version = version+1 WHERE account_id = ? AND security_id =? AND version =?";

                try (PreparedStatement stmt = connection.prepareStatement(upadateQuery)){
                    stmt.setInt(1, newPosition);
                    stmt.setString(2, accountId);
                    stmt.setString(3, securityId);
                    stmt.setInt(4, currentVersion);

                    int rowAffected = stmt.executeUpdate();
                    if (rowAffected > 0){
                        updated = true; // update successful
                    } else {
                        System.out.println("Optimistik lock failed, retrying for accountId: " + accountId + " and securityId: "+securityId);
                    }
                }
            } else {
                // 4. insert new query
                String insertQuery = "INSERT INTO positions (account_id, security_id, position) values (?,?,?)";
                try (PreparedStatement stmt = connection.prepareStatement(insertQuery)){
                    stmt.setString(1, accountId);
                    stmt.setString(2, securityId);
                    stmt.setInt(3, newPosition);
                    stmt.executeUpdate();
                    updated = true; // update successful
                }
            }
        }

    }
}
