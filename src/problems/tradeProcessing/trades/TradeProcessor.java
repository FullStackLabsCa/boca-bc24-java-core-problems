package problems.tradeProcessing.trades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tradeProcessing.customeinterface.files.JournalEntryServiceInterface;
import problems.tradeProcessing.customeinterface.files.SecuritiesReferenceService;
import problems.tradeProcessing.customeinterface.files.TradePayloadServiceInterface;
import problems.tradeProcessing.customeinterface.files.TradeProcessorInterface;
import problems.tradeoperations.manager.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessor implements TradeProcessorInterface, Runnable, TradePayloadServiceInterface, SecuritiesReferenceService, JournalEntryServiceInterface {

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
                insertJournalEntry(fields, securityId);
            }
        }
    }

    private String[] parsePayload(String payload) {
        return  payload.split(",");
    }


    @Override
    public String fetchTradePayload(String tradeId) throws SQLException {
        String query = "SELECT payload FROM trade_payloads WHERE trade_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, tradeId);
            ResultSet rs = stmt.executeQuery();
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
            stmt.setString(1, fields[2]); // Account number from parsed data
            stmt.setString(2, securityId); // Security ID from lookup
            stmt.setString(3, fields[4]); // Direction/activity from parsed data
            stmt.setString(4, fields[5]); // Quantity from parsed data
            stmt.setString(5, "posted");  // Assume a default posted status
            stmt.executeUpdate();
            journalEntryCount++;
            System.out.println("Journal entry inserted for trade ID: " + fields[0]);
        }

    }
}
