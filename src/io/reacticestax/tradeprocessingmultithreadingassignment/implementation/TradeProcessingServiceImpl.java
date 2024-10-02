package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.TradeProcessingService;

import java.io.IOException;
import java.sql.*;


public class TradeProcessingServiceImpl implements TradeProcessingService {

    private final String DB_URL = "jdbc:mysql://localhost:3306/bootcamp";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "password123";

    @Override
    public void insertToTradePayloadTableInDB(String trade_id, String status, String payload) throws IOException {
        String sql = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trade_id);
            pstmt.setString(2, status);
            pstmt.setString(3, payload);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new IOException("Error inserting trade payload into database", e);
        }
    }
}



