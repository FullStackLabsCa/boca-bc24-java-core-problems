package problems.trading_3way_with_object;

import problems.trading_3way_with_object.contract.TradeReader;
import problems.trading_3way_with_object.contract.TradeWriter;
import problems.trading_3way_with_object.utility.*;

import java.util.List;

public class TradeProcessor {

    public static void main(String[] args) {
        String filePath = FilePath.filePathValidator();
        double thresholdValue = ThresholdValidation.thresholdValue();
        // Create a Scanner object for getting user input from the command line
//        Scanner scanner = new Scanner(System.in);

//        String filePath = null;
//        double errorThreshold = -1;

//        // Loop to get valid file path
//        while (true) {
//            try {
//                // Prompt the user to enter the CSV file path
//                System.out.println("Enter the path to the file: ");
//                filePath = scanner.nextLine();
//
//                // File Reader Interface
//                TradeReader tradeReader = new CsvTradeReader();
//                List<Trade> trades = tradeReader.readTrades(filePath);
//
//                // If file is read successfully, break the loop
//                break;
//
//            } catch (FileNotFoundException e) {
//                System.err.println("Error: File not found. Please check the path and try again.");
//            } catch (Exception e) {
//                System.err.println("An unexpected error occurred while reading the file: " + e.getMessage());
//            }
//        }

//        // Loop to get a valid error threshold
//        while (true) {
//            try {
//                // Prompt the user to enter the error threshold
//                System.out.println("Enter the error threshold (0-100): ");
//                errorThreshold = Double.parseDouble(scanner.nextLine());
//
//                // Validate the error threshold
//                if (errorThreshold < 0 || errorThreshold > 100) {
//                    throw new IllegalArgumentException("Error threshold must be between 0 and 100.");
//                }
//
//                // If valid threshold is provided, break the loop
//                break;
//
//            } catch (NumberFormatException e) {
//                System.err.println("Error: The error threshold must be a valid number. Please try again.");
//            } catch (IllegalArgumentException e) {
//                System.err.println(e.getMessage());
//            } catch (Exception e) {
//                System.err.println("An unexpected error occurred while entering the threshold: " + e.getMessage());
//            }
//        }

        // Continue with the rest of the process after getting valid inputs
        try {

            TradeReader tradeReader = new CsvTradeReader();
            List<Trade> trades = tradeReader.readTrades(filePath, thresholdValue);

            // Business Logic Validator and Writer
            TradeWriter tradeWriter = new TradeDatabaseWriter();
            int successfulInserts = tradeWriter.writeTrades(trades, thresholdValue);

            // Output the result
            System.out.println("Successful Inserts: " + successfulInserts);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the Scanner
//            scanner.close();
        }
    }
}
