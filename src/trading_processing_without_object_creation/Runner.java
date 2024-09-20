package trading_processing_without_object_creation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        int userThreshold = 0;
        boolean isvalid = false;
        try (Scanner scanner = new Scanner(System.in);) {
            while (!isvalid) {
                try {
                    System.out.println("Enter the Threshold Value");
                    userThreshold = scanner.nextInt();
                    isvalid = true;
                    DatabaseConnector.configureHikariCP();
                    TradeRepoLayer.insertTrade(DatabaseConnector.getConnection(), userThreshold);
                    TradeFileReader.countEntries();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input Please Try again...");
                    scanner.next();
                }
            }
        }

        // Step 2: Read file and load transactions into ArrayBlockingQueue
//        FileReader.readTransactionFileAndWriteToQueue("/Users/Gurpreet.Singh/source/credit_card_transactions.txt");
//        MultiThreading.startMultiThreadedProcessing();
    }
}