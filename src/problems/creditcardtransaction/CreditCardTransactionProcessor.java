package problems.creditcardtransaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class CreditCardTransactionProcessor {

    // Define a shared LinkedBlockingDeque
    private static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);

    // HikariCP DataSource
    private static HikariDataSource dataSource;

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        configureHikariCP();

        // Step 2: Read file and load transactions into Deque

        readTransactionFileAndWriteToQueue("/Users/Rushikumar.Patel/source/files/credit_card_transactions.txt");

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

        // Optional HikariCP settings
        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
    }

    // Step 1: Read transactions from a pipe-delimited file
    private static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("Adding transaction #" + counter + " in the queue >> " + creditCardTransaction);

                Thread.sleep(100); // Slowing down file read

                creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the deque
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}


//}

// Transaction POJO
class CreditCardTransaction {
    private String creditCardNumber;
    private String cardType;
    private String transactionType;
    private double amount;
    private double balance;

    public CreditCardTransaction(String creditCardNumber, String cardType, String transactionType, double amount, double balance) {
        this.creditCardNumber = creditCardNumber;
        this.cardType = cardType;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}

// Consumer thread that processes transactions
class TransactionConsumer implements Runnable {

    private Deque<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource;

    public TransactionConsumer(Deque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

//    @Override
//    public void run() {
//        while (true) {
//            try {
//                CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.removeFirst();  // Get transaction from deque
//                processTransaction(creditCardTransaction);  // Process transaction
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//                System.err.println("Transaction processing interrupted. Exiting...");
//                break;
//            }
//        }
//    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {  // Check if the thread has been interrupted
//            while(true)
            CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.pollFirst();  // Non-blocking retrieval

            if (creditCardTransaction != null) {
                processTransaction(creditCardTransaction);  // Process the transaction
            } else {
                try {
                    // Sleep briefly if the queue is empty to avoid tight looping
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    // Process each transaction with optimistic locking and retry logic
    private void processTransaction(CreditCardTransaction creditCardTransaction) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);


            try {
                // Step 1: Check if account exists
                int version = getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());

                if (version == -1) {
                    // Step 2: If no account exists, insert it
                    insertAccount(connection, creditCardTransaction);
                }

                // Step 3: Now update with optimistic locking
                updateAccountBalance(connection, creditCardTransaction, version);

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());


            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.addLast(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }

    } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the current version of the account for optimistic locking
    private int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
        String query = "SELECT version FROM accounts WHERE credit_card_number = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, creditCardNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("version");
        } else {
            return -1;  // Return -1 if no account exists
        }
    }

    // Insert a new account for a credit card number
    private void insertAccount(Connection connection, CreditCardTransaction creditCardTransaction) throws SQLException {
        connection.setAutoCommit(false);

        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.executeUpdate();
        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
        connection.commit();

    }

    // Update the account balance using optimistic locking
    private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
        connection.setAutoCommit(false);

        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
        stmt.setInt(3, version);

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0) {
            // Optimistic locking failed, retry
            connection.rollback();

            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
        }
        connection.commit();

    }
}

// Custom exception for optimistic locking failure
class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}
