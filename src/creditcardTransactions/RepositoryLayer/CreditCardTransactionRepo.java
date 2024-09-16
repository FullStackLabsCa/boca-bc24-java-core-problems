package creditcardTransactions.RepositoryLayer;
import creditcardTransactions.Model.CreditCardTransaction;
import creditcardTransactions.Utility.OptimisticLockingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardTransactionRepo {
    // Get the current version of the account for optimistic locking
    public static int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
        String query = "SELECT version FROM accounts WHERE credit_card_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, creditCardNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("version");
                } else {
                    return -1; // No account found
                }
            }
        }
    }
    // Insert a new account for a credit card number
    public  static  void insertAccount(Connection connection, CreditCardTransaction creditCardTransaction) throws SQLException {
        connection.setAutoCommit(false);
        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setString(1, creditCardTransaction.getCreditCardNumber());
        stmt.setDouble(2, creditCardTransaction.getBalance() - creditCardTransaction.getAmount());
        stmt.executeUpdate();
        System.out.println("Inserted new account for card: " + creditCardTransaction.getCreditCardNumber());connection.commit();
//        connection.setAutoCommit(true);
    }

    // Update the account balance using optimistic locking
    public  static  void updateAccountBalance(Connection connection, CreditCardTransaction creditCardTransaction, int version) throws SQLException {
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
