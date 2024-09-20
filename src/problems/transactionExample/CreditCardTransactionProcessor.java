package problems.transactionExample;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.sql.*;
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
        readTransactionFileAndWriteToQueue("/Users/Dhruv.Desai/Downloads/credit_card_transactions.txt");

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

    // Getters
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

    private ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource;

    public TransactionConsumer(ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        while (!creditCardTransactionQueue.isEmpty()) {
            try {
                CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();  // Get transaction from queue
                processTransaction(creditCardTransaction);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
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
                if(version!=-1){
                    // Step 3: Now update with optimistic locking
                        updateAccountBalance(connection, creditCardTransaction, version);
                }

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                try {

                 Thread.sleep((long)(Math.random()*100));
            }  catch (InterruptedException interruptedException){
                 Thread.currentThread().interrupt();
                }
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.put(creditCardTransaction);
                connection.rollback();
            }

            catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
        catch (SQLException | InterruptedException e) {
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
            // Return -1 if no account exists for the given credit card number
            return -1;
        }
    }

    // Insert a new account for a credit card number
    private void insertAccount(Connection connection, CreditCardTransaction creditCardTransaction) throws SQLException {
        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version, card_type, transaction_type) VALUES (?, ?, 0, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.setString(3, creditCardTransaction.getCardType());
        stmt.setString(4, creditCardTransaction.getTransactionType());
        stmt.executeUpdate();
        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
//        connection.commit();
//        connection.setAutoCommit(true);
    }

    // Update the account balance using optimistic locking
    private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
        String balanceUpdate= "SELECT balance FROM accounts WHERE credit_card_number= ?";

        double currentBal= 0;
        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";

        PreparedStatement preparedStatement= connection.prepareStatement(balanceUpdate);
        preparedStatement.setString(1, creditCardTransaction.getCreditCardNumber());
        ResultSet resultSet= preparedStatement.executeQuery();

        while (resultSet.next()){
            currentBal= resultSet.getDouble("balance");
        }
        System.out.println("Before update:");
        System.out.println("Current Balance: " + currentBal);
        System.out.println("Transaction Amount: " + creditCardTransaction.getAmount());
        System.out.println("New Balance: " + (currentBal - creditCardTransaction.getAmount()));


        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setDouble(1, currentBal - creditCardTransaction.getAmount());
        System.out.println(currentBal);
        System.out.println(creditCardTransaction.getAmount());
        System.out.println(currentBal - creditCardTransaction.getAmount());
        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
        stmt.setInt(3, version);

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0) {
            // Optimistic locking failed, retry
            connection.rollback();
            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
        }
//        connection.commit();
//        connection.setAutoCommit(true);
    }
}

// Custom exception for optimistic locking failure
class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}
