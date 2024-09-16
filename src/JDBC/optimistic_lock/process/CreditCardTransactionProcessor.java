package JDBC.optimistic_lock.process;

import com.zaxxer.hikari.HikariConfig;
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


    public static void startMultiThreadedProcessing() {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
        }

        executorService.shutdown();
    }

    // Configure HikariCP connection pool
    public static void configureHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/Bank");
        config.setUsername("root");
        config.setPassword("password123");

        // Optional HikariCP settings
        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(3000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
    }

    // Step 1: Read transactions from a pipe-delimited file
    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
//                System.out.println("adding transaction #"+counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionQueue.put(creditCardTransaction);
                // Place transaction in the queue
//                System.out.println(creditCardTransactionQueue.take());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Consumer thread that processes transactions


