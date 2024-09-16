package problems.jdbc.optimisticlocking.models;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.optimisticlocking.service.CreditCardTransactionService;

import java.util.concurrent.LinkedBlockingDeque;

public class TransactionConsumer implements Runnable {

    private final LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue;
    private final HikariDataSource dataSource;

    public TransactionConsumer(LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();  // Get transaction from queue
                CreditCardTransactionService.processTransaction(creditCardTransaction, creditCardTransactionQueue, dataSource);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
