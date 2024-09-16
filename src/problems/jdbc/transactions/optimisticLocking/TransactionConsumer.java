package problems.jdbc.transactions.optimisticLocking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TransactionConsumer implements Runnable {
    private ConcurrentLinkedDeque<CreditCardTransaction> creditCardTransactionQueue;
    private DatabaseManager databaseManager;

    public TransactionConsumer(ConcurrentLinkedDeque<CreditCardTransaction> creditCardTransactionQueue, DatabaseManager databaseManager) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.databaseManager = databaseManager;
    }

    @Override
    public void run() {
        while (true) {
            CreditCardTransaction transaction = creditCardTransactionQueue.poll();
            if (transaction == null) {
                break;
            }
            try {
                processTransaction(transaction);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void processTransaction(CreditCardTransaction transaction) {
        try (Connection connection = databaseManager.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                int version = getAccountVersion(connection, transaction.getCreditCardNumber());
                if (version == -1) {
                    insertAccount(connection, transaction);
                } else {
                    updateAccountBalance(connection, transaction, version);
                }
                connection.commit();
                System.out.println("Transaction processed for card: " + transaction.getCreditCardNumber());
            } catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                creditCardTransactionQueue.offerFirst(transaction);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
        String query = "SELECT version FROM accounts WHERE credit_card_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, creditCardNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("version");
                } else {
                    return -1;
                }
            }
        }
    }

    private void insertAccount(Connection connection, CreditCardTransaction transaction) throws SQLException {
        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setString(1, transaction.getCreditCardNumber());
            stmt.setDouble(2, transaction.getBalance() - transaction.getAmount());
            stmt.executeUpdate();
            System.out.println("Inserted new account for card: " + transaction.getCreditCardNumber());
        } catch (Exception e) {
            connection.rollback();
            throw new problems.jdbc.transactions.optimisticLocking.OptimisticLockingException("insert error - optimistic throw error");
        }
    }

    private void updateAccountBalance(Connection connection, CreditCardTransaction transaction, int version) throws SQLException {
        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setDouble(1, transaction.getBalance() - transaction.getAmount());
            stmt.setString(2, transaction.getCreditCardNumber());
            stmt.setInt(3, version);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                throw new OptimisticLockingException("Optimistic locking failed, retrying transaction...");
            }
        }
    }
}
