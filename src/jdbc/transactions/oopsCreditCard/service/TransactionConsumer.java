package jdbc.transactions.oopsCreditCard.service;

import jdbc.transactions.oopsCreditCard.exceptions.OptimisticLockingException;
import jdbc.transactions.oopsCreditCard.model.CreditCardTransaction;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.concurrent.LinkedBlockingDeque;

public class TransactionConsumer implements Runnable {

    private LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource;

    public TransactionConsumer(LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        while (true) {
            if(creditCardTransactionQueue.isEmpty()){
                break;
            }
            try {
                CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();  // Get transaction from queue
                processTransaction(creditCardTransaction);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Process each transaction with optimistic locking and retry logic
    private void processTransaction(CreditCardTransaction creditCardTransaction) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                // Step 1: Check if account exists
                int version = getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());
                if (version == -1) {
                    // Step 2: If no account exists, insert it
                    insertAccount(connection, creditCardTransaction);
                }else{
                    // Step 3: Now update with optimistic locking
                    updateAccountBalance(connection, creditCardTransaction, version);
                }
                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException | SQLIntegrityConstraintViolationException e ) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.putLast(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Get the current version of the account for optimistic locking
    private int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
        String query = "SELECT version FROM accounts WHERE credit_card_number = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, creditCardNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("version");
        } else {
            // Return -1 if no account exists for the given credit card number
            return -1;
        }
    }

    // Insert a new account for a credit card number
    private void insertAccount(Connection connection, CreditCardTransaction creditCardTransaction) throws SQLException {
        try{
            String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, creditCardTransaction.getCreditCardNumber());
            stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
            stmt.executeUpdate();
            System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
//            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException("Duplicate Entry");
        }

//        connection.setAutoCommit(true);
    }

    // Update the account balance using optimistic locking
    private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
//        connection.setAutoCommit(false);
        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
        stmt.setInt(3, version);

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0) {
            // Optimistic locking failed, retry
            connection.rollback();
            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
        }
//        connection.commit();
//        connection.setAutoCommit(true);
    }
}