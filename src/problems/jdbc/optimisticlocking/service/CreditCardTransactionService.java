package problems.jdbc.optimisticlocking.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.optimisticlocking.database.DatabaseConnection;
import problems.jdbc.optimisticlocking.exceptions.OptimisticLockingException;
import problems.jdbc.optimisticlocking.models.CreditCardTransaction;
import problems.jdbc.optimisticlocking.models.TransactionConsumer;
import problems.jdbc.optimisticlocking.repository.CreditCardTransactionRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreditCardTransactionService {
    // Define a shared LinkedBlockingDeque
    private static final LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);

    public static void setupDBConnectionAndRunFileReadingAndExecuteThreads() {
        // Step 1: Configure HikariCP connection pool
        HikariDataSource dataSource = DatabaseConnection.configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        CreditCardTransactionService.readTransactionFileAndWriteToQueue("/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src/problems/jdbc/optimisticlocking/assets/credit_card_transactions.txt");

        CreditCardTransactionService.startMultiThreadedProcessing(dataSource);
    }

    // Step 1: Read transactions from a pipe-delimited file
    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("Amount")) {
                    counter++;
                    String[] data = line.split("\\|");
                    CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                    System.out.println("adding transaction #" + counter + "in the queue >> " + creditCardTransaction);
                    creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the queue
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startMultiThreadedProcessing(HikariDataSource dataSource) {
        // Step 3: Start multiple consumer threads to process transactions
        int numberOfThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
        }

        executorService.shutdown();
    }

    // Process each transaction with optimistic locking and retry logic
    public static void processTransaction(CreditCardTransaction creditCardTransaction, LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);

            try {
                // Step 1: Check if account exists
                int version = CreditCardTransactionRepository.getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());

                if (version == -1) {
                    // Step 2: If no account exists, insert it
                    CreditCardTransactionRepository.insertAccount(connection, creditCardTransaction);
                } else {
                    // Step 3: Now update with optimistic locking
                    CreditCardTransactionRepository.updateAccountBalance(connection, creditCardTransaction, version);
                }

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.putFirst(creditCardTransaction);
            } catch (SQLIntegrityConstraintViolationException e) {
                connection.rollback();
                creditCardTransactionQueue.putFirst(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                creditCardTransactionQueue.putFirst(creditCardTransaction);
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
