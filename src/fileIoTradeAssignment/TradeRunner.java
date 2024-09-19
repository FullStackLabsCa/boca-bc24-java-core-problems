package fileIoTradeAssignment;

import java.io.IOException;
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
            processor.readFile();
            processor.tradesInsertionMaker();
        } catch (IOException | SQLException e) {
            System.err.println("An error occurred while processing the file: " + e.getMessage());
        }

        // Close the scanner at the end
        scanner.close();
    }
}