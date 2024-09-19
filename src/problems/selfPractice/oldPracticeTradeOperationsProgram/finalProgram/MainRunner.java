package problems.selfPractice.oldPracticeTradeOperationsProgram.finalProgram;

import java.sql.Connection;
import java.sql.SQLException;

public class MainRunner {
    public static void main(String[] args) {
        // Step- : Database connection
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.configureHikariCP();

        // Test connection
        try (Connection conn = databaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }

        // Step- : Taking user input
        UserInputCMD userInputCMD = new UserInputCMD(databaseConnection);
    }
}
