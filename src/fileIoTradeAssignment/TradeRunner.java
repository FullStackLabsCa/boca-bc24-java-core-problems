package fileIoTradeAssignment;

import java.io.FileNotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class TradeRunner {
    public static void main(String[] args) {

        TradeProcessor processor = new TradeProcessor();
        Scanner scanner = new Scanner(System.in);

        // Ask the user to specify the file path
        System.out.print("Please enter the file path for the trades CSV file: ");
        String filePath = scanner.nextLine();
        processor.setFilePath(filePath);

        // Process trades from the specified file
        try {
            processor.readTradeFile();
            processor.tradesInsertionMaker();
        } catch (FileNotFoundException | SQLException e) {
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        } catch (HitReadFileErrorsThresholdException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}


