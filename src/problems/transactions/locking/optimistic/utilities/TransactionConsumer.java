package problems.transactions.locking.optimistic.utilities;

import com.zaxxer.hikari.HikariDataSource;
import problems.transactions.locking.optimistic.exceptions.OptimisticLockingException;
import problems.transactions.locking.optimistic.pojo.CreditCardTransaction;

import java.sql.*;
import java.util.concurrent.LinkedBlockingDeque;

// Consumer thread that processes transactions
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
            if (creditCardTransactionQueue.isEmpty()) { // added by mohan; otherwise it is going in infinitive loop
                break;
            } else {
                CreditCardTransaction creditCardTransaction = null;  // Get transaction from queue
                try {
                    creditCardTransaction = creditCardTransactionQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                processTransaction(creditCardTransaction);  // Process transaction
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
                } else {                                   // Mohan added this else block around existing updateAccountBalance statement
                    updateAccountBalance(connection, creditCardTransaction, version);
                }

                // Step 3: Now update with optimistic locking

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.putFirst(creditCardTransaction);
            } catch (SQLIntegrityConstraintViolationException e) {
                System.err.println(e.getMessage());
                connection.rollback();
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.putFirst(creditCardTransaction);

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
        //connection.setAutoCommit(false);     //Mohan: Commented, since it is aleardy se to false
        //String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
        //System.out.println("processing Txn Insert Mode= " + creditCardTransaction);                // added by mohan
        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        try {

            int rowsUpdated = stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
            //System.out.println("Integrity Constrain Violation failed while inserting a new record, retrying transaction...");
            System.out.println("Record has been processed and added by another thread, retrying to update");

            //When SQLIntegrityConstraintViolationException happens that means when we started processing this transaction, there was no entry for this credit card in the DB, but while commit
            //there is a record, hence the record already processed.
            throw new SQLIntegrityConstraintViolationException(sqlIntegrityConstraintViolationException.getMessage());
        }

        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
        //connection.commit();                  // Mohan: Commented, as we are commit in the transaction processer; this is duplicate
        //connection.setAutoCommit(true);
    }

    // Update the account balance using optimistic locking
    private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
        //connection.setAutoCommit(false);
        //System.out.println("processing Txn Update Mode= " + creditCardTransaction);   //added by mohan
        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
        stmt.setInt(3, version);

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0) {
            // Optimistic locking failed, retry
            connection.rollback();
            throw new OptimisticLockingException("Optimistic locking failed while updating existing, retrying transaction...");
        }
        //connection.commit();
        //connection.setAutoCommit(true);
    }
}
