package problems.jdbc.transactions.locking_optimistic;

import com.zaxxer.hikari.HikariDataSource;

import java.util.concurrent.ArrayBlockingQueue;

public class TransactionConsumer implements Runnable{

    private ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue;
    private TransactionProcessor dataSource;

    public TransactionConsumer(ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue, TransactionProcessor dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();  // Get transaction from queue
                TransactionProcessor.processTransaction(creditCardTransaction);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
