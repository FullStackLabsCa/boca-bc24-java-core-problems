package transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class MultiThreading {

    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionDeque = FileReader.creditCardTransactionDeque;
    private static DatabaseConnection databaseConnection =new DatabaseConnection();
    private  static TransactionRepoLayer transactionRepoLayer= new TransactionRepoLayer();

    public static void startMultiThreadedProcessing() {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);


        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionServiceLayer(creditCardTransactionDeque,databaseConnection,transactionRepoLayer));

        }

        executorService.shutdown();

//        try {
//            // Wait for existing tasks to terminate
//            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
//                executorService.shutdownNow(); // Forcefully shut down if not terminated
//                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
//                    System.err.println("Executor service did not terminate.");
//                }
//            }
//        } catch (InterruptedException e) {
//            executorService.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
    }

}
