package jdbc.optimisticlocking.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.optimisticlocking.databaseconnection.DatabaseConnection;
import jdbc.optimisticlocking.exceptions.OptimisticLockingException;
import jdbc.optimisticlocking.model.CreditCardTransaction;
import jdbc.optimisticlocking.repository.CreditCardRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreditCardService {

    private static ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue = new ArrayBlockingQueue<>(5000);;
    private static HikariDataSource dataSource;

    public static void setupDbConnectionAndReadFile() {
        // Step 1: Configure HikariCP connection pool
        dataSource = DatabaseConnection.configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/optimisticlocking/credit_card_transactions.txt");

        startMultiThreadedProcessing();
    }


    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction #"+counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the queue
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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

    public static void processTransaction(CreditCardTransaction creditCardTransaction) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                // Step 1: Check if account exists
                int version = CreditCardRepository.getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());

                if (version == -1) {
                    // Step 2: If no account exists, insert it
                    CreditCardRepository.insertAccount(connection, creditCardTransaction);
                } else {
                    // Step 3: Now update with optimistic locking
                    CreditCardRepository.updateAccountBalance(connection, creditCardTransaction, version);
                }

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.put(creditCardTransaction);
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                    System.err.println("Duplicate entry detected for credit card number " + creditCardTransaction.getCreditCardNumber());
                }
                connection.rollback();
                creditCardTransactionQueue.put(creditCardTransaction);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
