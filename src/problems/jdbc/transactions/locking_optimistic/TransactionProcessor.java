package problems.jdbc.transactions.locking_optimistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class TransactionProcessor {


    private static DatabaseManager databaseManager;

    // Define a shared ArrayBlockingQueue
    static ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue = new ArrayBlockingQueue<>(5000);

    // Constructor to initialize the DatabaseManager
    public TransactionProcessor(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    // Process each transaction with optimistic locking and retry logic
    static void processTransaction(CreditCardTransaction creditCardTransaction) {
        try (Connection connection = databaseManager.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                // Step 1: Check if account exists
                int version = getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());

                if (version == -1) {
                    // Step 2: If no account exists, insert it
                    databaseManager.insertAccount(creditCardTransaction);
//                    insertAccount(connection, creditCardTransaction);
                }

                // Step 3: Now update with optimistic locking
                databaseManager.updateAccountBalance(creditCardTransaction, version);

                connection.commit();  // Commit transaction
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                // Put the transaction back in the queue for retry
                creditCardTransactionQueue.put(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Get the current version of the account for optimistic locking
    private static int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
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
}
