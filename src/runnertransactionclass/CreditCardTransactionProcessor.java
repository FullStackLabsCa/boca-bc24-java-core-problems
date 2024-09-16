package runnertransactionclass;

import com.zaxxer.hikari.HikariDataSource;
import datasource.DataSourceTransaction;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class CreditCardTransactionProcessor {
    private static final LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);
    private static final HikariDataSource dataSource = DataSourceTransaction.createDataSource();

    public static void main(String[] args) {
        String filePath = "/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/utility/credit_card_transactions.txt";
        readTransactionFileAndWriteToQueue(filePath);
        startMultiThreadedProcessing();
    }

    private static void startMultiThreadedProcessing() {
        int numberOfThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
        }

        executorService.shutdown();
    }

    private static void readTransactionFileAndWriteToQueue(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header line
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                CreditCardTransaction transaction = CreditCardTransaction.fromCsv(line);
                System.out.println("Adding transaction #" + counter + " to the queue >> " + transaction);
                creditCardTransactionQueue.put(transaction);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
