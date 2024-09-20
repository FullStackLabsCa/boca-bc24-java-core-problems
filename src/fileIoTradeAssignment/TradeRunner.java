package fileIoTradeAssignment;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class TradeRunner {
    public static void main(String[] args) {

        TradeProcessor processor = new TradeProcessor();
        Scanner scanner = new Scanner(System.in);
        boolean fileFound = false;


        try {

            // Keep asking the user for the correct file path until the file is found
            while (!fileFound) {

                System.out.print("Please enter the file path for the trades CSV file: ");
                try {
                    String filePath = scanner.nextLine();
                    processor.setFilePath(filePath);
                    processor.readTradeFile();
                    processor.tradesInsertionMaker();
                    fileFound = true;
                } catch (FileNotFoundException e) {
                    System.err.println("File not found. Please try again.");
                } catch (SQLException e) {
                    System.err.println("An SQL error occurred while processing the file: " + e.getMessage());
                } catch (HitReadFileErrorsThresholdException e) {
                    System.out.println(e.getMessage());
                } catch (HitDatabaseInsertErrorsThresholdException e) {
                    System.err.println(e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }
}







