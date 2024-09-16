package creditcardTransactions.PresentationLayer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.Model.CreditCardTransaction;
import creditcardTransactions.ServiceLayer.TransactionConsumer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import static creditcardTransactions.ServiceLayer.CreditCardService.readTransactionFileAndWriteToQueue;

public class CreditCardTransactionProcessorRunner {
    // Define a shared ArrayBlockingQueue
  //  public static ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue = new ArrayBlockingQueue<>(5000);
    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue =  new LinkedBlockingDeque<>(5000);
    // HikariCP DataSource
    public static HikariDataSource dataSource;

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        configureHikariCP();
        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/creditcardTransactions/Utility/credit_card_transactions.txt");
        startMultiThreadedProcessing();
    }
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
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
        config.setUsername("root");
        config.setPassword("newpassword");
        // Optional HikariCP settings
        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
    }


}
