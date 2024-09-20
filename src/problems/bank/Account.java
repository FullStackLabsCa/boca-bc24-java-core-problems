package problems.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    Connection conn;

    public Account(Connection conn){
        this.conn= conn;
    }

    public void getBalance(int account_id) throws SQLException {
        double bal=0;

        String query = "SELECT balance from bank_account_details WHERE account_id= ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Current Balance in account id " + account_id + " is: " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch balance for account: " + account_id + ".");
            System.out.println(e.getMessage());
        }
    }
}
