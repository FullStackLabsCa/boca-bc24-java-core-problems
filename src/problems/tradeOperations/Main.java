package problems.tradeOperations;

import problems.tradeOperations.manager.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {

        // Database connection
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


        // Thresold Input - either user(CMD) or application,properties
        // Below 2 lines have error for more information refer : note1.md
//        ThresholdInput thresholdInput = new ThresholdInput();
//        double effectiveThreshold = thresholdInput.threshholdUserFileInput();
        double effectiveThreshold = ThresholdInput.threshholdUserFileInput(args);

        // File Path user(CMD) input
        UserInputCMD userInputCMD = new UserInputCMD(databaseManager, effectiveThreshold);

    }

}
