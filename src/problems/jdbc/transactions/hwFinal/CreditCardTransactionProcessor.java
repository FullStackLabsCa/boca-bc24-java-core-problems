package problems.jdbc.transactions.hwFinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreditCardTransactionProcessor {
    private static ConcurrentLinkedDeque<CreditCardTransaction> creditCardTransactionQueue = new ConcurrentLinkedDeque<>();
    private static DatabaseManager databaseManager = new DatabaseManager();

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        databaseManager.configureHikariCP();

        // Step 2: Read file and load transactions into queue
        try {
            readTransactionFileAndWriteToQueue(Paths.get("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/jdbc/transactions/hwFinal/credit_card_transactions.txt").toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Step 3: Start multi-threaded processing
        startMultiThreadedProcessing();
    }

    private static void startMultiThreadedProcessing() {
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, databaseManager));
        }

        executorService.shutdown();
    }

    private static void readTransactionFileAndWriteToQueue(String filePath) throws IOException {
        int counter = 0;

        try (var reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction transaction = new CreditCardTransaction(
                        data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4])
                );
                System.out.println("Adding transaction #" + counter + " to the queue >> " + transaction);
                Thread.sleep(10);
                creditCardTransactionQueue.offer(transaction);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
