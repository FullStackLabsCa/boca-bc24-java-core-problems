package practice.multi_threading.trading_processing_assignment.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserInputValidator {

    public static String getFilePath() {
        Scanner scanner = new Scanner(System.in);
        String filePath = null;
        boolean validInput = false;

        // Loop to get a valid file path
        while (!validInput) {
            try {
                System.out.println("Enter the path to the file: ");
                filePath = scanner.nextLine();

                if (Files.exists(Paths.get(filePath))) {
                    validInput = true;
                } else {
                    System.err.println("Error: File not found. Please check the path and try again.");
                }
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        return filePath;
    }

    public static int getNumberOfChunks() {
        Scanner scanner = new Scanner(System.in);
        int numberOfChunks = 0;
        boolean validInput = false;

        // Loop to get a valid number of chunks
        while (!validInput) {
            try {
                System.out.println("Enter the Number of Chunks (1 to 20): ");

                if (scanner.hasNextInt()) {
                    numberOfChunks = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (numberOfChunks >= 1 && numberOfChunks <= 20) {
                        validInput = true;
                    } else {
                        System.err.println("Error: Number of chunks must be between 1 and 20. Please try again.");
                    }
                } else {
                    System.err.println("Error: Invalid number of chunks. Please enter an integer.");
                    scanner.next(); // Clear the invalid input
                }
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                scanner.next(); // Clear the invalid input
            }
        }

        return numberOfChunks;
    }
}
