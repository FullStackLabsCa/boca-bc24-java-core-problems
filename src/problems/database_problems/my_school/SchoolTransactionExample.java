package problems.database_problems.my_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SchoolTransactionExample {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bootcamp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password123";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            connection.setAutoCommit(false);


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
