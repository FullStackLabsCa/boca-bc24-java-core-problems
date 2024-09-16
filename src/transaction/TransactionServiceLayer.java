package transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TransactionServiceLayer implements Runnable {

    public  LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionDeque;
    private  DatabaseConnection databaseConnection;
    private  TransactionRepoLayer transactionRepoLayer;

    public TransactionServiceLayer(LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionDeque, DatabaseConnection databaseConnection, TransactionRepoLayer transactionRepoLayer) {
        this.creditCardTransactionDeque = creditCardTransactionDeque;
        this.databaseConnection = databaseConnection;
        this.transactionRepoLayer = transactionRepoLayer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                CreditCardTransaction creditCardTransaction = creditCardTransactionDeque.take();  // Get transaction from queue
                processTransaction(creditCardTransaction);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Process each transaction with optimistic locking and retry logic
    private void processTransaction(CreditCardTransaction creditCardTransaction)  {
        try (Connection connection = databaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                // Step 1: Check if account exists
                int version = transactionRepoLayer.getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());

                if (version == -1) {
                    // Step 2: If no account exists, insert it
                    transactionRepoLayer.insertAccount(connection, creditCardTransaction);
                }

                // Step 3: Now update with optimistic locking
                transactionRepoLayer.updateAccountBalance(connection, creditCardTransaction, version);

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionDeque.putFirst(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
