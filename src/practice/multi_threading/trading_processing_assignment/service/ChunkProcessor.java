package practice.multi_threading.trading_processing_assignment.service;

import practice.multi_threading.trading_processing_assignment.connection_pool.HikariCPDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChunkProcessor implements Runnable {

    private final String chunkFileName;
    private final HikariCPDataSource dataSource;

    public ChunkProcessor(String chunkFileName, HikariCPDataSource dataSource) {
        this.chunkFileName = chunkFileName;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        try {
            // Read the chunk file
            List<String> chunkLines = Files.readAllLines(Paths.get(chunkFileName));

            // Validate each line and update database
            for (String line : chunkLines) {
                boolean isValid = validateLine(line);
                updateStatusInDatabase(line, isValid);
            }


        } catch (IOException e) {
            System.err.println("Error reading chunk file: " + chunkFileName);
            System.out.println("ChunkProcessor.run() = " + e.getMessage());
        }
    }

    // Validation: Ensure that each CSV line has exactly 7 fields
    private boolean validateLine(String line) {
        String[] fields = line.split(",");
        return fields.length == 7;
    }

    // Update the status in the database based on validation
    private void updateStatusInDatabase(String csvLine, boolean isValid) {
        String status = isValid ? "valid" : "invalid";
        String statusReason = isValid ? "" : "Trade line does not contain 7 fields";

        String query = "INSERT INTO trade_payloads (trade_id, status, status_reason, payload) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String tradeId = csvLine.split(",")[0];  // Assuming trade_id is the first field

            stmt.setString(1, tradeId);  // trade_id
            stmt.setString(2, status);   // status
            stmt.setString(3, statusReason);  // status_reason
            stmt.setString(4, csvLine);  // full payload (the csv line)

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating status for trade: " + csvLine);
            e.printStackTrace();
        }
    }
}
