package problems.tradeoperations;

import problems.tradeoperations.exception_files.HitErrorsThresholdException;
import problems.tradeoperations.manager.DatabaseConnection;
import problems.tradeoperations.tradefiles.TradeRWFile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws HitErrorsThresholdException, SQLException {

        DatabaseConnection.create("3306","tradesDB");
        Connection connection = DatabaseConnection.getConnection();
        // Thresold Input - either user(CMD) or application,properties
        double effectiveThreshold = ThresholdInput.threshholdUserFileInput(args);
        // File Path user(CMD) input
        new TradeRWFile(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        System.out.println("/Users/Jay.Shah/Downloads/trades_sample_1000.csv");
        System.out.println("src/problems/tradeoperations/sourcesfiles/trades_sample.csv");
        String filePath = scanner.nextLine();
        try (connection) {
            System.out.println("Connection to database established successfully.");
            // Create an instance of TradeRWFile with the database connection
            TradeRWFile.readFileStatic(filePath, effectiveThreshold, connection); // Adjust threshold as needed
        } catch (HitErrorsThresholdException e) {
            System.out.println("Error threshold exceeded: " + e.getMessage());
        } finally {
            DatabaseConnection.closeDataSource();
        }
    }
}
