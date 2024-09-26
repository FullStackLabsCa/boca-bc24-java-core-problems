package creditcardTransactions.ServiceLayer;

import creditcardTransactions.Model.CreditCardTransaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static creditcardTransactions.PresentationLayer.CreditCardTransactionProcessorRunner.creditCardTransactionQueue;
import static creditcardTransactions.PresentationLayer.CreditCardTransactionProcessorRunner.dataSource;

public class CreditCardService {


    // Step 1: Read transactions from a pipe-delimited file
    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if(!line.contains("Amount")) {
                    counter++;
                    String[] data = line.split("\\|");

                    CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                    System.out.println("adding transaction #" + counter + "in the queue >> " + creditCardTransaction);
                     Thread.sleep(10);
                    creditCardTransactionQueue.putFirst(creditCardTransaction);  // Place transaction in the queue
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void startMultiThreadedProcessing() {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
