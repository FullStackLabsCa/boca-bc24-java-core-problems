package problems.transactions.locking.optimistic.utilities;

import problems.transactions.locking.optimistic.pojo.CreditCardTransaction;
import problems.transactions.locking.optimistic.CreditCardTransactionProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utilities {


    public static void startMultiThreadedProcessing() {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(CreditCardTransactionProcessor.creditCardTransactionQueue, CreditCardTransactionProcessor.dataSource));
        }

        executorService.shutdown();
    }

    // Static method to Read transactions from a pipe-delimited file and load it to Deque
    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //added this line to skip the header record
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction # " + counter + "in the queue >> " + creditCardTransaction);
                Thread.sleep(10);
                CreditCardTransactionProcessor.creditCardTransactionQueue.add(creditCardTransaction);  // Place transaction in the queue

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
