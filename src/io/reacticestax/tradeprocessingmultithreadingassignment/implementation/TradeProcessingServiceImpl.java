package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.TradeProcessingService;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TradeProcessingServiceImpl implements TradeProcessingService , Runnable {
ChunkProcessorImpl chunkProcessorImpl;

    public void insertToRawTableInDB(long trade_id, String status, String payload) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(chunkProcessorImpl.chunkFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the first column is trade_id and the last column is status
                String[] columns = line.split(",");
                trade_id = Long.parseLong(columns[0]);
                status = String.valueOf(columns[7]);
                payload = line;
            }
            // Log the values before inserting
            System.out.println("Inserting into trade_payloads: tradeId=" + trade_id + ", status=" + status + ", payload=" + payload);

            String sql = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp", "root", "password123");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setLong(1, trade_id);
                pstmt.setString(2, status);
                pstmt.setString(3, payload);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }
}



