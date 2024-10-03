package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.ConfigLoader;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeProcessor implements Runnable {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bootcamp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password123";
    static ConfigLoader configLoader = new ConfigLoader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/Application.properties");
    public static final Integer tradeProcessorThreadsCount = configLoader.getIntProperty("number.of.trade.processor.threads");
    public static final ExecutorService tradeProcessorThreadPool = Executors.newFixedThreadPool(tradeProcessorThreadsCount);
    TradeDistributor tradeDistributor;
    String tradePayload;

    @Override
    public void run() {
        String tradeId = "";
        try {
            while (true) {
                tradeId = TradeDistributor.getTradeQueue1().take();
                System.out.println("received tradeId = " + tradeId + " in tradeProcessor");
                processTradeForTradeId(tradeId);
            }
//            TradeDistributor.getTradeQueue1()tradeDistributor.tradeQueue2.take();
//            TradeDistributor.getTradeQueue1().take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processTradeForTradeId(String tradeId) {
        try {
            assert !tradeId.isBlank();
            String trade_Payload = selectPayloadFromTradePayloads(tradeId);
            String[] payloadData = trade_Payload.split(",");
            lookupSecurityReference(payloadData[3]);
            insertToJournalEntry(payloadData[2], payloadData[3], payloadData[4], Integer.parseInt(payloadData[5]), "not_posted", LocalDateTime.parse(payloadData[1] , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), payloadData[0]);
            updatePositionInPositions(payloadData[2], payloadData[3],payloadData[4],Integer.parseInt(payloadData[5]),LocalDateTime.parse(payloadData[1],DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public String selectPayloadFromTradePayloads(String tradeId) throws SQLException {
        String query = "Select payload from trade_payloads where trade_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                tradePayload = rs.getString("payload");
            }
        }
            return tradePayload;
    }


    public boolean lookupSecurityReference(String cusip) throws SQLException {
        String queryForSecurityLookup = "SELECT COUNT(*) FROM securities_custom_reference WHERE cusip = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt2 = conn.prepareStatement(queryForSecurityLookup)) {
            stmt2.setString(1, cusip);
            try (ResultSet rs = stmt2.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }


    public void insertToJournalEntry(String account_number,
                                     String security_cusip, String direction,
                                     int quantity, String posted_status,
                                     LocalDateTime transaction_time, String trade_id) {
        String insertIntoJournalEntryQuery = "INSERT INTO journal_entry (account_number, security_cusip, direction, quantity, posted_status, transaction_time, trade_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt3 = conn.prepareStatement(insertIntoJournalEntryQuery)) {

            // Setting parameters for the prepared statement
            stmt3.setString(1, account_number);
            stmt3.setString(2, security_cusip);
            stmt3.setString(3, direction);
            stmt3.setInt(4, quantity);
            stmt3.setString(5, posted_status);
            stmt3.setTimestamp(6, Timestamp.valueOf(transaction_time));
            stmt3.setString(7, trade_id);

            // Execute the update
            stmt3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("data insertion completed in journal_entry");
    }


    public void updatePositionInPositions(String account_number, String securityCusip, String direction, int quantity, LocalDateTime posted_time) {
        String selectPositionQuery = "SELECT positions FROM positions WHERE account_number = ? AND security_cusip = ?";
        String updatePositionQuery = "UPDATE positions SET positions = ?, posted_time = ?, version = version + 1 WHERE account_number = ? AND security_cusip = ?";
        String insertPositionQuery = "INSERT INTO positions (account_number, security_cusip, positions, posted_time, version) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement selectStmt = conn.prepareStatement(selectPositionQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updatePositionQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertPositionQuery)) {


            selectStmt.setString(1, account_number);
            selectStmt.setString(2, securityCusip);
            ResultSet rs = selectStmt.executeQuery();

            int newPosition;
            if (rs.next()) {

                int currentPosition = rs.getInt("positions");

                if ("buy".equalsIgnoreCase(direction)) {
                    newPosition = currentPosition + quantity;
                } else if ("sell".equalsIgnoreCase(direction)) {
                    newPosition = currentPosition - quantity;
                } else {
                    throw new IllegalArgumentException("Invalid trade direction: " + direction);
                }


                updateStmt.setInt(1, newPosition);
                updateStmt.setTimestamp(2, Timestamp.valueOf(posted_time));
                updateStmt.setString(3, account_number);
                updateStmt.setString(4, securityCusip);
                updateStmt.executeUpdate();
                System.out.println("Position updated for account: " + account_number + ", security: " + securityCusip);
            } else {

                if ("buy".equalsIgnoreCase(direction)) {
                    newPosition = quantity;
                } else if ("sell".equalsIgnoreCase(direction)) {
                    newPosition = -quantity;
                } else {
                    throw new IllegalArgumentException("Invalid trade direction: " + direction);
                }


                insertStmt.setString(1, account_number);
                insertStmt.setString(2, securityCusip);
                insertStmt.setInt(3, newPosition);
                insertStmt.setTimestamp(4, Timestamp.valueOf(posted_time));
                insertStmt.setInt(5, 1);  //  version is 1 for a new record
                insertStmt.executeUpdate();
                System.out.println("New position inserted for account: " + account_number + ", security: " + securityCusip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

