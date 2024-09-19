package creditcardtransaction;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class TransactionConsumer implements Runnable{

    private ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource;

    public TransactionConsumer(ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        while (true) {
            if (creditCardTransactionQueue.isEmpty()) {
                creditCardTransactionQueue = null;
                break;
            } else {
                try {
                    CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();  // Get transaction from queue
                    processTransaction(creditCardTransaction);  // Process transaction
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    // Process each transaction with optimistic locking and retry logic
    private void processTransaction(CreditCardTransaction creditCardTransaction) {
        int retry = 0;
        int max = 5;
        boolean transactionCompleted = false;

        while (retry < max && !transactionCompleted) {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("Setting autoCommit to false for transaction");
                connection.setAutoCommit(false);  // Ensure auto-commit is disabled for each retry
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                try {
                    // Step 1: Check if account exists
                    int version = getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());

                    if (version == -1) {
                        // Step 2: If no account exists, insert it
                        insertAccount(connection, creditCardTransaction);
                    }
                    if(version!=-1){
                        updateAccountBalance(connection, creditCardTransaction, version);
                    }
                    // Step 3: Now update with optimistic locking

                    System.out.println("AutoCommit before commit: " + connection.getAutoCommit());

                    connection.commit();  // Commit transaction
                    transactionCompleted = true;  // Mark the transaction as completed successfully
                    System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

                } catch (OptimisticLockingException e) {
                    System.err.println(e.getMessage());
                    retry++;
                    if (retry < max) {
                        // Add a random delay before retrying
                        try {
                            Thread.sleep((long) (Math.random() * 100)); // Sleep for a random time between 0-100ms
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        System.err.println("Max retry limit reached for card: " + creditCardTransaction.getCreditCardNumber());
                        // After max retries, put the transaction back to the queue
                        try {
                            System.out.println("Re-queuing the transaction for card: " + creditCardTransaction.getCreditCardNumber());
                            creditCardTransactionQueue.put(creditCardTransaction); // Put back the transaction into the queue
                        } catch (InterruptedException interruptedException) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    connection.rollback();  // Rollback the transaction in case of failure

                } catch (SQLException e) {
                    try {
                        if (!connection.getAutoCommit()) {  // Ensure rollback only happens if autoCommit is false
                            connection.rollback();  // Rollback transaction
                        }
                    } catch (SQLException rollbackException) {
                        rollbackException.printStackTrace();
                    }
                    e.printStackTrace();

                } finally {
                    // Reset auto-commit only after the transaction is done or failed
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Get the current version of the account for optimistic locking
    private int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
        String query = "SELECT version FROM accounts WHERE credit_card_number = ? FOR UPDATE";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, creditCardNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("version");
        }
        // Return -1 if no account exists for the given credit card number
        return -1;
    }


    // Insert a new account for a credit card number
    private void insertAccount(Connection connection, CreditCardTransaction creditCardTransaction) throws SQLException {
        //  connection.setAutoCommit(false);
        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version,card_type,transaction_type) VALUES (?, ?, 0, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.setString(3,creditCardTransaction.getCardType());
        stmt.setString(4,creditCardTransaction.getTransactionType());
        stmt.executeUpdate();
        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
//        connection.commit();
//        connection.setAutoCommit(true);
    }

    // Update the account balance using optimistic locking
    private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
        String updatedBalance = "SELECT balance,transaction_type from accounts WHERE credit_card_number = ?";
        double updatedBalanceAccount = 0;
        String transactionType = "";
        PreparedStatement preparedStatement = connection.prepareStatement(updatedBalance);

        preparedStatement.setString(1,creditCardTransaction.getCreditCardNumber());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {

            updatedBalanceAccount = resultSet.getDouble("balance");
        }
        double newBalance;
        if ("buy".equalsIgnoreCase(creditCardTransaction.getTransactionType())) {
            // For "buy" transactions, subtract the amount
            newBalance = updatedBalanceAccount - creditCardTransaction.getAmount();
        } else {
            // For other transactions (e.g., refund, deposit), add the amount
            newBalance = updatedBalanceAccount + creditCardTransaction.getAmount();
        }

        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);

            stmt.setDouble(1, newBalance);



        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
        stmt.setInt(3, version);
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0) {
            // Optimistic locking failed, retry
            connection.rollback();
            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
        }
    }
}
