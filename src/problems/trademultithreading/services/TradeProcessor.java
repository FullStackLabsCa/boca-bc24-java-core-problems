package problems.trademultithreading.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import static problems.trademultithreading.databasehelpers.DatabaseHelper.dataSource;

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
                String securityIdCusip = validateTrade(tradeData);

                if (securityIdCusip != null) {
                    // Step 3: Insert into journal_entry table if valid
                    insertIntoJournalEntry(tradeData, securityIdCusip);
                } else {
                    System.out.println("Trade " + tradeId + " failed validation.");
                }



            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("TradeProcessor for " + queueName + " interrupted.");
                break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String fetchTradeFromDatabase(String tradeId) {
        try (Connection conn = dataSource.getConnection()) {

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

    private String validateTrade(String tradeData) {
        if (tradeData != null) {
            String[] tradeFields = tradeData.split(",");
            if (tradeFields.length >= 4) {
                String cusip = tradeFields[3];
                return isCusipValid(cusip);
            }
        }
        return null;
    }

    private String isCusipValid(String cusip) {

        try (Connection conn = dataSource.getConnection()) {

            if (conn == null) {
                System.out.println("Database connection is null. Cannot validate CUSIP.");
                return null;
            }
            String selectQuery = "SELECT * "+
                    "FROM securitiesReference WHERE cusip = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                pstmt.setString(1, cusip);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
//                    String security_id_cusip = rs.getString("cusip");
//                    System.out.println("Security id for cusip :- " + security_id_cusip);
                    return rs.getString("cusip");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error validating CUSIP: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private void insertIntoJournalEntry(String tradeData, String securityIdCusip) throws SQLException {
        String[] tradeFields = tradeData.split(",");
        if (tradeFields.length >= 6) {
            String accountNumber = tradeFields[2];
            String direction = tradeFields[4];
            int quantity = Integer.parseInt(tradeFields[5]);


            try (Connection conn = dataSource.getConnection()) {
                if (conn != null) {
                    String insertQuery = "INSERT INTO journal_entry (accountNumber, securityId, direction, quantity, postedStatus) " +
                            "VALUES (?, ?, ?, ?, ?) ";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                        pstmt.setString(1, accountNumber);
                        pstmt.setString(2, securityIdCusip);
                        pstmt.setString(3, direction);
                        pstmt.setInt(4, quantity);
                        pstmt.setString(5, "posted");

                        int rowsInserted = pstmt.executeUpdate();

                        System.out.println("Inserted trade into journal_entry. Rows inserted: " + rowsInserted);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error inserting trade into journal_entry:- Duplicate entry to the Journal entry table ");
            }
            // Step 4 : Insert into Positions Table
            startInsertingPosition(tradeData, securityIdCusip);
        } else {
            System.out.println("Trade data does not contain enough fields to insert into journal_entry.");
        }
    }

    private void startInsertingPosition(String tradeData, String securityId) throws SQLException {
        String[] tradeFields = tradeData.split(",");
        if (tradeFields.length >= 6) {
            String accountNumber = tradeFields[2];
            String direction = tradeFields[4]; // Buy sell
            int quantityFromPayload = Integer.parseInt(tradeFields[5]); // quantity from payload
            int version = 0;

            try (Connection conn = dataSource.getConnection()) {
                    if (conn != null) {
                        String selectQuery = "SELECT position, version FROM positions WHERE accountNumber = ? AND securityId = ?";
                        String updateQuery  = "UPDATE positions SET  position=?, version = ? WHERE accountNumber = ? and securityId = ?";
                        String insertQuery = "INSERT INTO positions (accountNumber, securityId, position, version) VALUES(?,?,?,?) ";
                        try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                            selectStmt.setString(1, accountNumber);
                            selectStmt.setString(2, securityId);

                            ResultSet rs = selectStmt.executeQuery();

                            int quantityFromTable = 0;

                            if(rs.next()){
                                quantityFromTable = rs.getInt("position");

                                if(direction.equalsIgnoreCase("BUY")){
                                    quantityFromTable+=quantityFromPayload;
                                }else if (direction.equalsIgnoreCase("SELL")) {
                                    quantityFromTable -= quantityFromPayload;
                                } else {
                                    System.out.println("Invalid trade direction: " + direction);
                                    return;
                                }
                                version += 1;
                                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

                                    updateStmt.setInt(1, quantityFromTable);
                                    updateStmt.setInt(2, version);
                                    updateStmt.setString(3, accountNumber);
                                    updateStmt.setString(4, securityId);

                                    int rowsUpdated = updateStmt.executeUpdate();
                                    System.out.println(rowsUpdated + "--U rows updated into Positions Table");
                                }
                            }else{
                                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

                                    insertStmt.setString(1,accountNumber);
                                    insertStmt.setString(2,securityId);
                                    insertStmt.setInt(3,quantityFromPayload);
                                    insertStmt.setInt(4,version);

                                    int rowsInserted = insertStmt.executeUpdate();
                                    System.out.println(rowsInserted+"--I rows inserted into Positions Table");
                                }
                            }
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error inserting trade into Position Table: " + e.getMessage());
                    e.printStackTrace();
                }
            } else{
                System.out.println("Trade data does not contain enough fields to insert into Positions table.");
        }
    }
}
