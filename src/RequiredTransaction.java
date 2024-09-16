import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequiredTransaction {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "newpassword";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Disable auto-commit, starting a transaction
            connection.setAutoCommit(false);

            // First method call
            performActionA(connection) ;{
                System.out.println("action A running");
            }
            // Second method call within the same transaction
            performActionB(connection);{
                System.out.println("action B running");
            }

            // Commit the transaction after both actions
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void performActionA(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET balance = balance - 100 WHERE account_id = 1")) {
            stmt.executeUpdate();
        }
    }

    public static void performActionB(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET balance = balance + 100 WHERE account_id = 2")) {
            stmt.executeUpdate();
        }
    }
}
