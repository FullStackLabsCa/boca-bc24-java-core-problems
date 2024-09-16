package problems.jdbc.transactions.locking_optimistic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static problems.jdbc.transactions.locking_optimistic.TransactionFileReader.readTransactionFileAndWriteToQueue;

public class CreditCardTransactionProcessor {

    private static final int QUEUE_CAPACITY = 5000;
    private static final int THREAD_POOL_SIZE = 5;
    public static void main(String[] args) {

        // Step 1: Configure HikariCP
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.configureHikariCP();

        // Step 2: Create a shared queue for transactions
        ArrayBlockingQueue<CreditCardTransaction> transactionQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        // Step 3: Create TransactionProcessor instance
        TransactionProcessor transactionProcessor = new TransactionProcessor(databaseManager);

        String filePath = "/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/jdbc/transactions/locking_optimistic/credit_card_transactions.txt";  // Correct path to the file

        // Step 4: Read transactions from file and add them to the queue
        TransactionFileReader fileReader = new TransactionFileReader();
        readTransactionFileAndWriteToQueue(filePath, transactionQueue);

        // Step 5: Start multi-threaded consumers to process the queue
        startMultiThreadedProcessing(transactionQueue, transactionProcessor);

        // Step 1: Configure HikariCP connection pool
        DatabaseManager.configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/jdbc/transactions/locking_optimistic/credit_card_transactions.txt", transactionQueue);

        startMultiThreadedProcessing(transactionQueue, transactionProcessor);
    }

    private static void startMultiThreadedProcessing(ArrayBlockingQueue<CreditCardTransaction> transactionQueue, TransactionProcessor transactionProcessor) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE );

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            executorService.submit(new TransactionConsumer(transactionQueue, transactionProcessor));
        }

        executorService.shutdown();
    }
}

// Custom exception for optimistic locking failure
class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}
