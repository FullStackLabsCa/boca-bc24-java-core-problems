package practice.jdbc.transactions.locking.optimistic;

import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.concurrent.*;

public class CreditCardTransactionProcessor {
    // Define a shared LinkedBlockingDeque with a maximum capacity
    public static BlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);
    private static HikariDataSource dataSource;

    public static void startFileReadingThread(String filePath) {
        Thread fileReadingThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while (true) {
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split("\\|");
                        CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2],
                                Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                        System.out.println("Adding transaction to queue: " + creditCardTransaction);
                        creditCardTransactionQueue.putLast(creditCardTransaction);  // Place transaction in the end of the queue
                    }
//                     Sleep for a bit before checking the file again
                    Thread.sleep(5000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        fileReadingThread.start();
    }

    public static void startMultiThreadedProcessing() {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
        }

        executorService.shutdown();
    }


}




