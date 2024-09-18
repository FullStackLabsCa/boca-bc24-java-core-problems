//package problems.jdbc;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Deque;
//import java.util.LinkedList;
//import java.util.concurrent.*;
//
//public class CreditCardTransactionProcessor {
//
//    // Define a shared ArrayBlockingQueue
//    // made change here
//    private static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);
//
//    // HikariCP DataSource
//    private static HikariDataSource dataSource;
//
//    public static void main(String[] args) {
//        // Step 1: Configure HikariCP connection pool
//        configureHikariCP();
//
//        // Step 2: Read file and load transactions into ArrayBlockingQueue
//        readTransactionFileAndWriteToQueue("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/jdbc/credit_card_transactions.txt");
//
//        startMultiThreadedProcessing();
//    }
//
////    private static void startMultiThreadedProcessing() {
////        // Step 3: Start multiple consumer threads to process transactions
////        int numberOfThreads = 5;
////        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
////
////        for (int i = 0; i < numberOfThreads; i++) {
////            //made change here
////            executorService.submit(new TransactionConsumer(creditCardTransactionQueue, dataSource));
////        }
////
////        executorService.shutdown();
////    }
//
////    // Configure HikariCP connection pool
////    private static void configureHikariCP() {
////        HikariConfig config = new HikariConfig();
////        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
////        config.setUsername("root");
////        config.setPassword("password123");
////
////        // Optional HikariCP settings
////        config.setMaximumPoolSize(10); // Max 10 connections in the pool
////        config.setMinimumIdle(5); // Minimum idle connections
////        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
////        config.setIdleTimeout(600000); // 10 minutes idle timeout
////
////        dataSource = new HikariDataSource(config);
////    }
//
//    // Step 1: Read transactions from a pipe-delimited file
//    public static void readTransactionFileAndWriteToQueue(String filePath) {
//        int counter = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            reader.readLine();
//            while ((line = reader.readLine()) != null) {
//                counter++;
//                String[] data = line.split("\\|");
//                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
//                System.out.println("adding transaction # " + counter + " in the queue >> " + creditCardTransaction);
////                Thread.sleep(100);
//                //add element to last - made change here
//                creditCardTransactionQueue.putFirst(creditCardTransaction);  // Place transaction in the queue
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
////// Transaction POJO
////class CreditCardTransaction {
////    private String creditCardNumber;
////    private String cardType;
////    private String transactionType;
////    private double amount;
////    private double balance;
////
////    public CreditCardTransaction(String creditCardNumber, String cardType, String transactionType, double amount, double balance) {
////        this.creditCardNumber = creditCardNumber;
////        this.cardType = cardType;
////        this.transactionType = transactionType;
////        this.amount = amount;
////        this.balance = balance;
////    }
//
//    // Getters
////    public String getCreditCardNumber() {
////        return creditCardNumber;
////    }
////
////    public String getCardType() {
////        return cardType;
////    }
////
////    public String getTransactionType() {
////        return transactionType;
////    }
////
////    public double getAmount() {
////        return amount;
////    }
////
////    public double getBalance() {
////        return balance;
////    }
//
////    @Override
////    public String toString() {
////        return "Transaction{" +
////                "creditCardNumber='" + creditCardNumber + '\'' +
////                ", cardType='" + cardType + '\'' +
////                ", transactionType='" + transactionType + '\'' +
////                ", amount=" + amount +
////                ", balance=" + balance +
////                '}';
////    }
////}
//
//// Consumer thread that processes transactions
//class TransactionConsumer implements Runnable {
//
//    private LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue;
//    private HikariDataSource dataSource;
//
//    public TransactionConsumer(LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
//        //made change here
//        this.creditCardTransactionQueue = creditCardTransactionQueue;
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();  // Get transaction from queue
//                processTransaction(creditCardTransaction);  // Process transaction
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                break;
//            }
//        }
//    }
//
//    // Process each transaction with optimistic locking and retry logic
////    private void processTransaction(CreditCardTransaction creditCardTransaction) {
////        try (Connection connection = dataSource.getConnection()) {
////            connection.setAutoCommit(false);
////            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
////
////            try {
////                // Step 1: Check if account exists
////                int version = getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());
////                if (version == -1) {
////                    // Step 2: If no account exists, insert it
////                    insertAccount(connection, creditCardTransaction);
////                }else {
////                    updateAccountBalance(connection, creditCardTransaction, version);
////                }
////                // Step 3: Now update with optimistic locking
////                connection.commit();  // Commit transaction
////                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());
////
////            } catch (OptimisticLockingException e) {
////                System.err.println(e.getMessage());
////                // Put the transaction back in the queue for retry
////                //made change here
////                creditCardTransactionQueue.putFirst(creditCardTransaction);
////            } catch (SQLException e) {
////                connection.rollback();
////                creditCardTransactionQueue.putFirst(creditCardTransaction);
//////                e.printStackTrace();
////            }finally {
////                connection.setAutoCommit(true);
////            }
////        } catch (SQLException | InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//
//    // Get the current version of the account for optimistic locking
////    private int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
////        String query = "SELECT version FROM accounts WHERE credit_card_number = ?";
////        PreparedStatement stmt = connection.prepareStatement(query);
////        stmt.setString(1, creditCardNumber);
////        ResultSet rs = stmt.executeQuery();
////        if (rs.next()) {
////            return rs.getInt("version");
////        } else {
////            // Return -1 if no account exists for the given credit card number
////            return -1;
////        }
////    }
//
//    // Insert a new account for a credit card number
////    private void insertAccount(Connection connection, CreditCardTransaction creditCardTransaction) throws SQLException {
////        connection.setAutoCommit(false);
////        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
////        PreparedStatement stmt = connection.prepareStatement(insertQuery);
////        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
////        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
////        stmt.executeUpdate();
////        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());
////      // connection.commit();
////      //  connection.setAutoCommit(false);
////    }
//
//    // Update the account balance using optimistic locking
////    private void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
////        connection.setAutoCommit(false);
////        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
////        PreparedStatement stmt = connection.prepareStatement(updateQuery);
////        stmt.setDouble(1, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
////        stmt.setString(2, creditCardTransaction.getCreditCardNumber());
////        stmt.setInt(3, version);
////
////        int rowsUpdated = stmt.executeUpdate();
////        if (rowsUpdated == 0) {
////            // Optimistic locking failed, retry
////            connection.rollback();
////            throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
////        }
//////        connection.commit();
////    //    connection.setAutoCommit(false);
////    }
////}
//
//// Custom exception for optimistic locking failure
////class OptimisticLockingException extends RuntimeException {
////    public OptimisticLockingException(String message) {
////        super(message);
////    }
////}
//
