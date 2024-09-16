package problems.jdbc.optimisticlocking;


import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.hikari.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static problems.jdbc.optimisticlocking.CreditCardRepository.*;

// Consumer thread that processes transactions
class TransactionServiceImpl implements TransactionService, Runnable {

    private ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource = DataSource.getDataSource();

    public TransactionServiceImpl(ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue) throws Exception {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
    }

    @Override
    public void run() {
        while (!creditCardTransactionQueue.isEmpty()) {
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
    public void processTransaction(CreditCardTransaction creditCardTransaction) {
        try (Connection connection = dataSource.getConnection()) {
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
            } catch (
                    OptimisticLockingException e) {
                System.err.println(e.getMessage() + creditCardTransaction.getCreditCardNumber());
//                creditCardTransactionQueue.put(creditCardTransaction);
                adjustTransactionQueue(creditCardTransaction);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void adjustTransactionQueue(CreditCardTransaction creditCardTransaction) throws InterruptedException {
        // This will be a temporary queue to store other elements
        Queue<CreditCardTransaction> tempQueue = new LinkedList<>();

        tempQueue.add(creditCardTransaction);  // Adding the element to the front of tempQueue
        // Add remaining elements back to the main queue
        while (!creditCardTransactionQueue.isEmpty()) {
            tempQueue.add(creditCardTransactionQueue.take()); // take will remove elements from queue too.
        }
        // Move elements from tempQueue back to ArrayBlockingQueue (new order)
        while (!tempQueue.isEmpty()) {
            creditCardTransactionQueue.put(tempQueue.poll()); //here is the issue queue is not updating
        }
    }


}