package problems.tradeOperations;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


        // Step- : Database connection
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.configureHikariCP();

        // Test connection
        try (Connection conn = databaseManager.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }

        // Step- : Taking user input
        UserInputCMD userInputCMD  = new UserInputCMD(databaseManager);


    }

}
