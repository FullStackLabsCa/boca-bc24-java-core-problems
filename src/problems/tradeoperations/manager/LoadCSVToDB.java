package problems.tradeoperations.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadCSVToDB {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3308/tradesDB"; // Update your DB info
        String username = "root";
        String password = "password123";
        String csvFilePath = "src/problems/tradeoperations/sourcesfiles/securities_reference.csv"; // Path to your CSV
        String sql = "INSERT INTO SecuritiesReference (symbol, description) VALUES (?, ?)"; // MySQL insert query
        int batchSize = 20;
        Connection connection = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);
             BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));) {
            // Establish database connection
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);  // Disable auto-commit for batch processing
            String lineText = null;
            int count = 0;
            // Skip the header line
            String header = lineReader.readLine();
            // Read CSV file line by line
            while ((lineText = header) != null) {
                String[] data = lineText.split(","); // Assuming CSV is comma-separated
                String symbol = data[0];
                String description = data[1];
                statement.setString(1, symbol);
                statement.setString(2, description);
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch(); // Execute batch after every batchSize rows
                }
                count++;
            }
            // Execute remaining batches
            statement.executeBatch();
            // Commit the transaction
            connection.commit();
            System.out.println("Data has been inserted successfully.");
        } catch (IOException ex) {
            System.err.println("Error reading CSV file.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
