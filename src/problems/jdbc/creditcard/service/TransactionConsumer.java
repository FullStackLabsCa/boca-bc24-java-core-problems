package jdbc.creditcard.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.creditcard.model.CreditCardTransaction;

import java.util.concurrent.ArrayBlockingQueue;

public class TransactionConsumer implements Runnable {

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
                CreditCardService.processTransaction(creditCardTransaction);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}