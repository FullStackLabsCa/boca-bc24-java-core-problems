package problems.trading_3way_with_object.utility;

import problems.trading_3way_with_object.Trade;
import problems.trading_3way_with_object.contract.TradeReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FilePath {
    public static String filePathValidator() {
        // Create a Scanner object for getting user input from the command line
        Scanner scanner = new Scanner(System.in);
        // Loop to get valid file path
        String filePath = null;
        double userErrorThreshold = 0;
        while (true) {
            try {
                // Prompt the user to enter the CSV file path
                System.out.println("Enter the path to the file: ");

                filePath = scanner.nextLine();

                // File Reader Interface
                TradeReader tradeReader = new CsvTradeReader();
                List<Trade> trades = tradeReader.readTrades(filePath,userErrorThreshold );

                // If file is read successfully, break the loop
                break;

            } catch (FileNotFoundException e) {
                System.err.println("Error: File not found. Please check the path and try again.");
            } catch (Exception e) {
                System.err.println("An unexpected error occurred while reading the file: " + e.getMessage());
            }
        }
        return filePath;
    }
}