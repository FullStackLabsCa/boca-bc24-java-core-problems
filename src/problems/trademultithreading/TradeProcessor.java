package problems.trademultithreading;

import problems.trademultithreading.databasehelpers.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class TradeProcessor implements Runnable {

    private final BlockingQueue<String> tradeQueue;
    private final String queueName;

    public TradeProcessor(BlockingQueue<String> tradeQueue, String queueName) {
        this.tradeQueue = tradeQueue;
        this.queueName = queueName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String tradeId = tradeQueue.take();
                System.out.println("Processing trade " + tradeId + " from " + queueName);

                // Step 1: Fetch the trade from the trade_payload table
                String tradeData = fetchTradeFromDatabase(tradeId);

                // Step 2: Validate the trade (check CUSIP in the securities_reference table)
                if (validateTrade(tradeData)) {
                    // Step 3: Insert into journal_entry table if valid
                    insertIntoJournalEntry(tradeData);
                } else {
                    System.out.println("Trade " + tradeId + " failed validation.");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("TradeProcessor for " + queueName + " interrupted.");
                break;
            }
        }
    }

    private String fetchTradeFromDatabase(String tradeId) {
        try (Connection conn = DatabaseHelper.getConnection()) {
            if (conn != null) {
                String selectQuery = "SELECT payload FROM trade_payload WHERE trade_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                    pstmt.setString(1, tradeId);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        return rs.getString("payload");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean validateTrade(String tradeData) {
        if (tradeData != null) {
            String[] tradeFields = tradeData.split(",");
            if (tradeFields.length >= 4) {
                String cusip = tradeFields[3];
                return isCusipValid(cusip);
            }
        }
        return false;
    }

    private boolean isCusipValid(String cusip) {
        try (Connection conn = DatabaseHelper.getConnection()) {
            if (conn == null) {
                System.out.println("Database connection is null. Cannot validate CUSIP.");
                return false;
            }
            String selectQuery = "SELECT COUNT(*) FROM securitiesReference WHERE cusip = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                pstmt.setString(1, cusip);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // true
                }
            }
        } catch (SQLException e) {
            System.out.println("Error validating CUSIP: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void insertIntoJournalEntry(String tradeData) {
        String[] tradeFields = tradeData.split(",");
        if (tradeFields.length >= 6) {
            String accountNumber = tradeFields[2];
            String securityId = tradeFields[3]; //cusip
            String direction = tradeFields[4];
            int quantity = Integer.parseInt(tradeFields[5]);


            try (Connection conn = DatabaseHelper.getConnection()) {
                if (conn != null) {
                    String insertQuery = "INSERT INTO journal_entry (accountNumber, securityId, direction, quantity, postedStatus) " +
                            "VALUES (?, ?, ?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE postedStatus = VALUES(postedStatus)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                        pstmt.setString(1, accountNumber);
                        pstmt.setString(2, securityId);
                        pstmt.setString(3, direction);
                        pstmt.setInt(4, quantity);
                        pstmt.setBoolean(5, true); // Assuming postedStatus is true when inserting

                        int rowsInserted = pstmt.executeUpdate();
                        System.out.println("Inserted trade into journal_entry. Rows inserted: " + rowsInserted);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error inserting trade into journal_entry: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Trade data does not contain enough fields to insert into journal_entry.");
        }
    }
}

