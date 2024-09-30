package io.reactivestax.jdbc.transactions.oopsCreditCard.main;

import io.reactivestax.jdbc.transactions.oopsCreditCard.service.TransactionConsumer;
import io.reactivestax.jdbc.transactions.oopsCreditCard.config.DatabaseHelper;
import io.reactivestax.jdbc.transactions.oopsCreditCard.model.CreditCardTransaction;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class CreditCardTransactionProcessor {

    // Define a shared ArrayBlockingQueue
    private static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);

    // HikariCP DataSource
    private static HikariDataSource dataSource;

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/transactions/locking/optimistic/credit_card_transactions.txt");
        startMultiThreadedProcessing();
    }

    private static void startMultiThreadedProcessing() {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
        }

        executorService.shutdown();
    }

    // Configure HikariCP connection pool
    private static void configureHikariCP() {
         dataSource = DatabaseHelper.getConnection();
    }

    // Step 1: Read transactions from a pipe-delimited file
    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction #"+counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionQueue.putFirst(creditCardTransaction);  // Place transaction in the queue
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}