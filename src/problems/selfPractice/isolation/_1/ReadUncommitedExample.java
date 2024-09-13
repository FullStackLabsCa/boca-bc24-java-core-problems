package problems.selfPractice.isolation._1;

import java.sql.*;

public class ReadUncommitedExample {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "password123";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Set the isolation level to READ UNCOMMITTED (allows dirty reads)
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            // Begin the transaction
            connection.setAutoCommit(false);

            // Step 1: Deduct from Account A
            String withdrawSQL = "UPDATE bank_account SET balance = balance - ? WHERE account_id = ?";
            try (PreparedStatement withdrawStmt = connection.prepareStatement(withdrawSQL)) {
                withdrawStmt.setDouble(1, 100);  // Withdraw 100 units
                withdrawStmt.setInt(2, 1);  // From Account A
                withdrawStmt.executeUpdate();

                // Simulate a delay
                System.out.println("Simulation Delay: ");
                Thread.sleep(10000); // 10 sec
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Step 2: Check balance of Account A
            String balanceSQL = "SELECT balance FROM bank_account WHERE account_id = 1 FOR UPDATE"; //pessimistic lock on that row
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
                        String depositSQL = "UPDATE bank_account SET balance = balance + ? WHERE account_id = ?";
                        try (PreparedStatement depositStmt = connection.prepareStatement(depositSQL)) {
                            depositStmt.setDouble(1, 100);  // Deposit 100 units
                            depositStmt.setInt(2, 2);  // Into Account B
                            depositStmt.executeUpdate();

                            // Simulate a delay
                            System.out.println("Simulation Delay: ");
                            Thread.sleep(10000); // 10 sec
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        // Step 4: Log the transaction
                        String logSQL = "INSERT INTO transactions_log (account_id, transaction_type, amount) VALUES (?, ?, ?)";
                        try (PreparedStatement logStmt = connection.prepareStatement(logSQL)) {
                            logStmt.setInt(1, 1);  // Log for Account A (Withdraw)
                            logStmt.setString(2, "Withdraw");
                            logStmt.setDouble(3, 100);
                            logStmt.executeUpdate();

                            logStmt.setInt(1, 2);  // Log for Account B (Deposit)
                            logStmt.setString(2, "Deposit");
                            logStmt.setDouble(3, 100);
                            logStmt.executeUpdate();
                        }

//                        // Step 5: Commit the transaction
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
