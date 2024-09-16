package jdbc.transactions;

import java.sql.*;

public class RequiredTransaction {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bootcamp";
        String username = "root";
        String password = "password123";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Disable auto-commit, starting a transaction
            connection.setAutoCommit(false);

            // First method call
            performActionA(connection);
            
            // Second method call within the same transaction
            performActionB(connection);

            // Commit the transaction after both actions
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void performActionA(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE bank_account SET balance = balance - 100 WHERE account_id = 1")) {
            stmt.executeUpdate();
        }
    }

    public static void performActionB(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * from bank_account ")) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getDouble("balance"));
                System.out.println(rs.getInt("account_id"));

            }
        }
    }
}