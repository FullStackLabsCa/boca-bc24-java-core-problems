package jdbc.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bootcamp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password123";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {

            // Disable auto-commit to manually control transactions
            connection.setAutoCommit(false);

            // Step 1: Deduct from Account A
            String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            try (PreparedStatement withdrawStmt = connection.prepareStatement(withdrawSQL)) {
                withdrawStmt.setDouble(1, 100);  // Withdraw 100 units
                withdrawStmt.setInt(2, 1);  // From Account A
                withdrawStmt.executeUpdate();
            }

            // Step 2: Check balance of Account A
            String balanceSQL = "SELECT balance FROM accounts WHERE account_id = 1 FOR UPDATE";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(balanceSQL)) {

                if (rs.next()) {
                    double balance = rs.getDouble("balance");

                    if (balance < 0) {
                        // If balance is negative, rollback
                        System.out.println("Insufficient funds, rolling back the transaction...");
                        connection.rollback();
                    } else {
                        // Step 3: Deposit into Account B
                        String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
                        try (PreparedStatement depositStmt = connection.prepareStatement(depositSQL)) {
                            depositStmt.setDouble(1, 100);  // Deposit 100 units
                            depositStmt.setInt(2, 2);  // Into Account B
                            depositStmt.executeUpdate();
                        }



                        // Step 5: Commit the transaction
                        connection.commit();
                        System.out.println("Transaction completed successfully.");
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




        }


    }
