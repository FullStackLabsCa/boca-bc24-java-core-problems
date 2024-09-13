package bank;

import bank.AccountService;
import config.DatabaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BankMain {
    public static void main(String[] args) throws SQLException, InterruptedException {
        final int accountId = 1; // Ensure this account exists in the database
        final double withdrawAmount = 100.0;
//        Connection connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password");


        try {
            Runnable withdrawTask = () -> {
                try(Connection connection = DatabaseHelper.getConnection()){
                    AccountService accountService = new AccountService(connection);

                    accountService.withDrawAmount(accountId, withdrawAmount);
                } catch (SQLException e) {
                    System.err.println(Thread.currentThread().getName() + ": Error during withdrawal: " + e.getMessage());
                }
            };

            // Create and start threads for concurrent withdrawals
            Thread thread1 = new Thread(withdrawTask, "Thread-1");
            Thread thread2 = new Thread(withdrawTask, "Thread-2");
            Thread thread3 = new Thread(withdrawTask, "Thread-3");

            thread1.start();
            thread2.start();
            thread3.start();

            // Wait for all threads to finish
            thread1.join();
            thread2.join();
            thread3.join();


//            new Account().getAccountDetails();

        } finally {
//            connection.close();
        }
    }
}
