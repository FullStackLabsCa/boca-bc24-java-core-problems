package bank;

import config.DatabaseHelper;

import java.sql.*;

public class AccountService {
private Account account;
private Transaction transaction;
private Connection connection;

    public AccountService(Connection connection) {
        this.connection = connection;
    }

    public void withDrawAmount(int accountId, double amount) throws SQLException {
        connection.setAutoCommit(false);
            try {
                Account account = new Account(accountId,connection);
                String sql = "SELECT balance, version FROM accounts WHERE account_id = ? FOR UPDATE"; // For update will lock the row.
                double balance;
                int version;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, accountId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        balance = resultSet.getDouble("balance");
                        version = resultSet.getInt("version");
                    } else {
                        throw new SQLException("Account not found.");
                    }
                }
                if (balance < 0 || balance - amount < 0) {
                    throw new RuntimeException("Not enough funds");
                }

                double newBalance = balance - amount;
                account.updateAccountBalance( newBalance, version);
                connection.commit();
                System.out.println(Thread.currentThread().getName() + ": Withdrawal completed successfully.");
            } catch (Exception e) {
                try {
                    connection.rollback(); // Only rollback if the transaction has not been committed
                    System.err.println(Thread.currentThread().getName() + ": Rollback due to error: " + e.getMessage());
                    if (e.getMessage().contains("version conflict")) {
                        System.err.println(Thread.currentThread().getName() + ": Retrying due to version conflict.");
                        // Retry logic kicks in, the loop continues
                    } else {
                        throw e; // If it's a different error, exit
                    }
                } catch (SQLException rollbackEx) {
                    System.err.println(Thread.currentThread().getName() + ": Error during rollback: " + rollbackEx.getMessage());
                } finally {
                    connection.setAutoCommit(true);
                }
            }

        }

}
