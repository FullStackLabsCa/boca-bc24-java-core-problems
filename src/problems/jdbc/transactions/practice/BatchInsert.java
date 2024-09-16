package problems.jdbc.transactions.practice;

import java.io.*;
import java.sql.*;

public class BatchInsert {

    private static final String INSERT_SQL = "INSERT INTO accounts (credit_card_number, card_type, balance) VALUES (?, ?, ?)";

    public static void main(String[] args) {
        // Initialize database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp2", "root", "password123")) {
            connection.setAutoCommit(false); // Set auto-commit to false for batch insert

            // Prepare the batch insert statement
            try (PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL)) {

                // Read the file and insert records in batch
                try (BufferedReader reader = new BufferedReader(new FileReader("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/jdbc/transactions/locking_optimistic/credit_card_transactions.txt"))) {
                    String line;

                    // Skip the first line (header)
                    reader.readLine();  // This will skip the first line

                    int batchSize = 100;  // Define batch size
                    int count = 0;

                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split("\\|");
                        pstmt.setString(1, data[0]);  // credit_card_number
                        pstmt.setString(2, data[1]);  // card_type
                        pstmt.setDouble(3, Double.parseDouble(data[4]));  // balance
                        pstmt.addBatch();

                        if (++count % batchSize == 0) {
                            pstmt.executeBatch();  // Execute batch insert
                        }
                    }
                    pstmt.executeBatch();  // Execute remaining batch if any
                    connection.commit();  // Commit the transaction
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();  // Rollback if there is an error
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

