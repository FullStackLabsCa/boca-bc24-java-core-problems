package Trading.presentationLayer;

import Trading.databaseConnectivity.DatabaseConnectivity;
import Trading.utility.HitErrorsThresholdException;
import Trading.utility.InvalidThresholdValueException;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Trading.serviceLayer.TradingService.fetchThresholdValue;
import static Trading.serviceLayer.TradingService.readTradingFileAndWriteToQueue;

public class TradingRunner {
    public static HikariDataSource dataSource;
    public static Double thresholdValue;

    public static void main(String[] args) {
        dataSource = DatabaseConnectivity.configureHikariCP();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose case: 1 for File Input, 2 for Configured Threshold");

        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (input == 1) {
                handleFileInput(scanner);
            } else if (input == 2) {
                handleConfiguredThreshold(scanner);
            } else {
                System.out.println("Enter only 1 or 2");
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter only 1 or 2");
        } catch (IOException | InvalidThresholdValueException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

    private static void handleFileInput(Scanner scanner) throws IOException {
        String filepath = getFilePath(scanner);
        thresholdValue = getThresholdValue(scanner);

        try {
            System.out.println(thresholdValue);
            readTradingFileAndWriteToQueue(filepath);
        } catch (SQLException | HitErrorsThresholdException e) {
            System.out.println("Error: File exceeded threshold value.");
        }
    }

    private static void handleConfiguredThreshold(Scanner scanner) throws InvalidThresholdValueException {
        String filepath = getFilePath(scanner);
        fetchThresholdValue();
    }

    private static String getFilePath(Scanner scanner) {
        String filepath;
        while (true) {
            System.out.println("Enter a file path:");
            filepath = scanner.nextLine();
            if (new File(filepath).exists()) {
                return filepath;
            } else {
                System.out.println("File not found.");
            }
        }
    }

    private static double getThresholdValue(Scanner scanner) {
        double value;
        while (true) {
            System.out.println("Enter threshold value (1-100):");
            try {
                value = scanner.nextDouble();
                if (value < 1 || value > 100) {
                    throw new InvalidThresholdValueException("Value must be between 1 and 100.");
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.next(); // Clear the invalid input
            } catch (InvalidThresholdValueException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
