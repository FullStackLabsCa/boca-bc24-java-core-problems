package creditcardTransactions.ServiceLayer;

import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.Model.CreditCardTransaction;
import creditcardTransactions.Utility.OptimisticLockingException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

import static creditcardTransactions.RepositoryLayer.CreditCardTransactionRepo.*;

// Consumer thread that processes transactions
public class TransactionConsumer implements Runnable {

   // private ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue;
    private LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource;

    public TransactionConsumer(LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
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
                }
                // Step 3: Now update with optimistic locking
                updateAccountBalance(connection, creditCardTransaction, version);
                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
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

    //
}
