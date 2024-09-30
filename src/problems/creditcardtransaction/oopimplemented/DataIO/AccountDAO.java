package problems.creditcardtransaction.oopimplemented.DataIO;

import problems.creditcardtransaction.oopimplemented.model.CreditCardTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public int getAccountVersion(Connection connection, String creditCardNumber) throws SQLException {
        String query = "SELECT version FROM accounts WHERE credit_card_number = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, creditCardNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("version");
        } else {
            return -1;
        }
    }

    public void insertAccount(Connection connection, CreditCardTransaction transaction) throws SQLException {
        String insertQuery = "INSERT INTO accounts (credit_card_number, balance, version) VALUES (?, ?, 0)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setString(1, transaction.getCreditCardNumber());
        stmt.setDouble(2, transaction.getBalance() - transaction.getAmount());
        stmt.executeUpdate();
    }

    public void updateAccountBalance(Connection connection, CreditCardTransaction transaction, int version) throws SQLException {
        String updateQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE credit_card_number = ? AND version = ?";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setDouble(1, transaction.getBalance() - transaction.getAmount());
        stmt.setString(2, transaction.getCreditCardNumber());
        stmt.setInt(3, version);
        stmt.executeUpdate();
    }
}

