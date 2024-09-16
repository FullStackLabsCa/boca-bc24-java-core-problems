package problems.jdbc.optimisticlocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static problems.jdbc.optimisticlocking.FileProcessor.readTransactionFileAndWriteToQueue;

public class CreditCardTransactionProcessor {

    // Define a shared ArrayBlockingQueue we can also use LinkedBlockingDeque instead of ArrayBlockingQueue
    private static ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue = new ArrayBlockingQueue<>(5000);


    public static void main(String[] args) throws Exception {

        String filePath = "/Users/Suraj.Adhikari/sources/credit_card_transactions.txt";

        creditCardTransactionQueue = readTransactionFileAndWriteToQueue(filePath, creditCardTransactionQueue);

        startMultiThreadedProcessing();
    }

    private static void startMultiThreadedProcessing() throws Exception {
        //Start multiple consumer threads to process transactions
        int numberOfThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionServiceImpl(creditCardTransactionQueue));
//            Thread.sleep(1000);
        }
        executorService.shutdown();
    }

}

