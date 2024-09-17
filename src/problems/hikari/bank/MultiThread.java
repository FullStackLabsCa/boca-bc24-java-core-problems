package problems.hikari.bank;

import problems.hikari.DatabaseConnectionPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static problems.hikari.bank.MainRunner.creditCardTransactionQueue;

public class MultiThread {
    public static void startMultiThreadedProcessing() throws Exception {
        //Start multiple consumer threads to process transactions
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumerService(creditCardTransactionQueue, DatabaseConnectionPool.getDataSource()));
        }

        executorService.shutdown();
    }
}
