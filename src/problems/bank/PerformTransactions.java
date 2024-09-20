package problems.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformTransactions {
    Connection conn;

    public PerformTransactions(Connection conn) {
        this.conn = conn;
    }

    public void withdraw(int accountId, Double amount) {
        String updateQuery = "UPDATE bank_account_details SET balance = balance - ? WHERE account_id = ?";
        String getNewBalanceQuery = "SELECT balance FROM bank_account_details WHERE account_id = ?";

        try {
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setDouble(1, amount);
            updateStatement.setInt(2, accountId);
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                PreparedStatement balanceStatement = conn.prepareStatement(getNewBalanceQuery);
                balanceStatement.setInt(1, accountId);
                ResultSet resultSet = balanceStatement.executeQuery();

                if (resultSet.next()) {
                    double newBalance = resultSet.getDouble(1);
                    System.out.println("Remaining balance after withdrawal is: " + newBalance);
                } else {
                    System.out.println("No balance found for account ID: " + accountId);
                }
            } else {
                System.out.println("No account found with ID: " + accountId);
            }
        } catch (SQLException e) {
            System.out.println("Unable to withdraw. " + e.getMessage());
        }
    }
}
