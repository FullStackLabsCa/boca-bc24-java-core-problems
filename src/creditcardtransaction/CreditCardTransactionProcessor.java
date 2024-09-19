package creditcardtransaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreditCardTransactionProcessor {

    // Define a shared ArrayBlockingQueue
    private static ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue = new ArrayBlockingQueue<>(5000);

    // HikariCP DataSource

    private static HikariDataSource dataSource;

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Abhay.Nimavat/IdeaProjects/StudentJavaCore/boca-bc24-java-core-problems/src/creditcardtransaction/credit_card_transactions.txt");

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
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
        config.setUsername("root");
        config.setPassword("password123");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
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
                creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the queue
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
