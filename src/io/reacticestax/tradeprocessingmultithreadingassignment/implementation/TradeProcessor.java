package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.ConfigLoader;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeProcessor implements Runnable {

    private final String DB_URL = "jdbc:mysql://localhost:3306/bootcamp";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "password123";
    static ConfigLoader configLoader = new ConfigLoader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/Application.properties");
    public static Integer tradeProcessorThreadsCount = configLoader.getIntProperty("number.of.trade.processor.threads");
    public static ExecutorService tradeProcessorThreadPool = Executors.newFixedThreadPool(tradeProcessorThreadsCount);
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
            Thread.currentThread().interrupt(); // Restore interrupted status
        }

        //String[] payloadData =tradePayload.split(",");


    }

    private void processTradeForTradeId(String tradeId) {
        try {
            assert !tradeId.isBlank();
            String tradePayload = selectPayloadFromTradePayloads(tradeId);
            String[] payloadData = tradePayload.split(",");
            lookupSecurityReference(payloadData[3]);
            insertToJournalEntry(payloadData[2], payloadData[3], payloadData[4], Integer.parseInt(payloadData[5]), "not_posted", Date.valueOf(payloadData[1]), payloadData[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void extractTradeIdFromTradeQueue() {
//
//
//
//    }

    public String selectPayloadFromTradePayloads(String tradeId) throws SQLException {
        String query = "Select payload from trade_payloads where trade_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            tradePayload = rs.getString(0);
            return tradePayload;
        }
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
                                     java.sql.Date transaction_time, String trade_id) {
        String insertIntoJournalEntryQuery = "INSERT INTO journal_entry (account_number, security_cusip, direction, quantity, posted_status, transaction_time, trade_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt3 = conn.prepareStatement(insertIntoJournalEntryQuery)) {

            // Setting parameters for the prepared statement
            stmt3.setString(1, account_number);
            stmt3.setString(2, security_cusip);
            stmt3.setString(3, direction);
            stmt3.setInt(4, quantity);
            stmt3.setString(5, posted_status);
            stmt3.setDate(6, transaction_time);
            stmt3.setString(7, trade_id);

            // Execute the update
            stmt3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("data insertion completed in journal_entry");
    }


//    public void updatePositionInPositions(){
//
//    }


}

