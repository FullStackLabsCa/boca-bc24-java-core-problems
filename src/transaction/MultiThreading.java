package transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

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
    }

}
