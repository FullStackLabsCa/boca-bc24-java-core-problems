package problems.tradeOperations;

import problems.tradeOperations.exceptionFiles.HitErrorsThresholdException;
import problems.tradeOperations.manager.DatabaseConnection;
import problems.tradeOperations.tradeFiles.TradeRWFile;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws HitErrorsThresholdException, SQLException {

        DatabaseConnection dbManager = new DatabaseConnection("3306","tradesDB");
        Connection connection = dbManager.getConnection();

        // Thresold Input - either user(CMD) or application,properties

        // Below 2 lines have error for more information refer : note1.md
//        ThresholdInput thresholdInput = new ThresholdInput();
//        double effectiveThreshold = thresholdInput.threshholdUserFileInput();

        double effectiveThreshold = ThresholdInput.threshholdUserFileInput(args);

        // File Path user(CMD) input
        TradeRWFile rwFile = new TradeRWFile(connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        System.out.println("/Users/Jay.Shah/Downloads/trades_sample_1000.csv");
        System.out.println("src/problems/tradeOperations/sourcesFiles/trades_sample.csv");
        String filePath = scanner.nextLine();
//        File file = new File(filePath);
//        rwFile.readFile(filePath, effectiveThreshold);

        try (connection) {
            System.out.println("Connection to database established successfully.");
            // Create an instance of TradeRWFile with the database connection
            TradeRWFile.readFileStatic(filePath, effectiveThreshold, connection); // Adjust threshold as needed

        } catch (HitErrorsThresholdException e) {
            System.out.println("Error threshold exceeded: " + e.getMessage());
        } finally {
            dbManager.closeDataSource();
        }

    }

}
