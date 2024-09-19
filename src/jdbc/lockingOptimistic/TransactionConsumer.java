package jdbc.lockingOptimistic;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;



    // Consumer thread that processes transactions
    class TransactionConsumer implements Runnable {

        private ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue;
        private HikariDataSource dataSource;

        public TransactionConsumer(ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
            this.creditCardTransactionQueue = creditCardTransactionQueue;
            this.dataSource = dataSource;
        }

        @Override
        public void run() {
            while (true) {
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

                } catch (OptimisticLockingException e) {
                    System.err.println(e.getMessage());


                } catch (SQLException e) {
                    // Put the transaction back in the queue for retry
                    creditCardTransactionQueue.put(creditCardTransaction);
                    connection.rollback();
                    e.printStackTrace();
                }
                finally{
                    connection.setAutoCommit(true);
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
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, creditCardTransaction.getCreditCardNumber());
            stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
            stmt.executeUpdate();
            System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
        }

        // Update the account balance using optimistic locking
        private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
            connection.setAutoCommit(false);
            String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
            stmt.setString(2, creditCardTransaction.getCreditCardNumber());
            stmt.setInt(3, version);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                // Optimistic locking failed, retry
                connection.rollback();
                connection.setAutoCommit(true);
                throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
            }
        }
    }


// Custom exception for optimistic locking failure
class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}

