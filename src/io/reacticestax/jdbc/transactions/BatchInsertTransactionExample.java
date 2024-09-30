package io.reacticestax.jdbc.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertTransactionExample {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp", "root", "password123");

            // Disable auto-commit mode to handle transaction manually
            connection.setAutoCommit(false);

            // Create SQL query for batch insert
            String insertSQL = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";

            // Prepare the statement
            preparedStatement = connection.prepareStatement(insertSQL);

            // First batch of inserts
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setString(2, "Engineering");
            preparedStatement.setDouble(3, 75000);
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Jane Smith");
            preparedStatement.setString(2, "HR");
            preparedStatement.setDouble(3, 60000);
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Michael Johnson");
            preparedStatement.setString(2, "Finance");
            preparedStatement.setDouble(3, 90000);
            preparedStatement.addBatch();

            // Execute batch
            int[] batchResults = preparedStatement.executeBatch();

            // Commit the transaction if all goes well
           connection.commit();
            System.out.println("Batch executed and transaction committed successfully.");

        } catch (SQLException e) {
            // Roll back the transaction in case of an error
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Transaction rolled back due to an error.");
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Clean up the resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}