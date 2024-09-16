//package problems.jdbc.transactions.locking_optimistic;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DatabaseManager {
//
//    private static HikariDataSource dataSource;
//
//    public DatabaseManager() {
//        configureHikariCP();
//    }
//
//    // Configure HikariCP connection pool
//    static void configureHikariCP() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp2");
//        config.setUsername("root");
//        config.setPassword("password123");
//
//        // Optional HikariCP settings
//        config.setMaximumPoolSize(10); // Max 10 connections in the pool
//        config.setMinimumIdle(5); // Minimum idle connections
//        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
//        config.setIdleTimeout(600000); // 10 minutes idle timeout
//
//        dataSource = new HikariDataSource(config);
//    }
//
//    // Method to get a database connection
//    public Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }
//
//    // Insert a new account for a credit card number
//    public void insertAccount(CreditCardTransaction creditCardTransaction) throws SQLException {
//        // insert logic
//        getConnection().setAutoCommit(false);
//        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
//        PreparedStatement stmt = getConnection().prepareStatement(insertQuery);
//        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
//        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
//        stmt.executeUpdate();
//        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
//        getConnection().commit();
//        getConnection().setAutoCommit(true);
//    }
//
//    // Update the account balance using optimistic locking
//    public void updateAccountBalance(CreditCardTransaction creditCardTransaction, int version) throws SQLException {
//        // update logic
//        getConnection().setAutoCommit(false);
//        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
//        PreparedStatement stmt = getConnection().prepareStatement(updateQuery);
//        stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
//        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
//        stmt.setInt(3, version);
//
//        int rowsUpdated = stmt.executeUpdate();
//        if (rowsUpdated == 0) {
//            // Optimistic locking failed, retry
//            getConnection().rollback();
//            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
//        }
//        getConnection().commit();
//        getConnection().setAutoCommit(true);
//    }
//
//    // Other database-related methods
//}


package problems.jdbc.transactions.locking_optimistic;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private static HikariDataSource dataSource;

    public DatabaseManager() {
        configureHikariCP();
    }

    // Configure HikariCP connection pool
    public static void configureHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp2");
        config.setUsername("root");
        config.setPassword("password123");

        // Optional HikariCP settings
        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
    }

    // Method to get a database connection
    Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Insert a new account for a credit card number
    public void insertAccount(CreditCardTransaction creditCardTransaction) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
            stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, creditCardTransaction.getCreditCardNumber());
            stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());

            stmt.executeUpdate();
            System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Update the account balance using optimistic locking
    public void updateAccountBalance(CreditCardTransaction creditCardTransaction, int version) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
            stmt = connection.prepareStatement(updateQuery);
            stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
            stmt.setString(2, creditCardTransaction.getCreditCardNumber());
            stmt.setInt(3, version);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                // Optimistic locking failed, retry
                connection.rollback();
                throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
