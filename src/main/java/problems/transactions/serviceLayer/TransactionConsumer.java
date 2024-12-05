package problems.transactions.serviceLayer;

import com.zaxxer.hikari.HikariDataSource;
import problems.transactions.model.CreditCardTransaction;
import problems.transactions.utility.OptimisticLockingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

import static problems.transactions.repositoryLayer.CreditCardTransactionRepository.insertAccount;
import static problems.transactions.repositoryLayer.CreditCardTransactionRepository.updateAccountBalance;

public class TransactionConsumer implements Runnable {
    private LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionLinkedBlockingDeque;
    private HikariDataSource dataSource;

    public TransactionConsumer(LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionLinkedBlockingDeque, HikariDataSource dataSource) {
        this.creditCardTransactionLinkedBlockingDeque = creditCardTransactionLinkedBlockingDeque;
        this.dataSource = dataSource;
    }

    //Process each transaction with optimistic locking and retry logic
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
                } else {
                    updateAccountBalance(connection, creditCardTransaction, version);
                }
                // Step 3: Now update with optimistic locking
                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                //made change here
                creditCardTransactionLinkedBlockingDeque.putFirst(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                creditCardTransactionLinkedBlockingDeque.putFirst(creditCardTransaction);
//                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

   //  Get the current version of the account for optimistic locking
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
    @Override
    public void run() {
        while (true) {
            try {
                CreditCardTransaction creditCardTransaction = creditCardTransactionLinkedBlockingDeque.take();  // Get transaction from queue
                processTransaction(creditCardTransaction);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
