import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequiresNewTransaction {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "newpassword";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Disable auto-commit to start transaction 1
            connection.setAutoCommit(false);

            // Perform action in transaction 1
            performActionA(connection);{
                System.out.println("action a");
            }
            
            // Commit transaction 1
            connection.commit();

            // Perform a new transaction (transaction 2)
            performActionWithNewTransaction();{
             //   System.out.println("action b");
            }
          
            //step1.1     //how to ensure step1.1 and 1.2 are still executed 
            //step1.2
            connection.commit();  //PENDING-to-be-checked

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void performActionA(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET balance = balance - 100 WHERE account_id = 1")) {
            stmt.executeUpdate();
        }
    }

    public static void performActionWithNewTransaction() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
        String username = "root";
        String password = "newpassword";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Disable auto-commit to start transaction 2
           connection.setAutoCommit(false);

            try (PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET balance = balance + 100 WHERE account_id = 2")) {
                stmt.executeUpdate();
            }

            // Commit transaction 2
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
