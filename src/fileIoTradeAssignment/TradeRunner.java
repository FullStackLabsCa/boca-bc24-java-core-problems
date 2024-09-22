package fileIoTradeAssignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class TradeRunner {
    public static void main(String[] args) {
        TradeProcessor processor = new TradeProcessor();
        Scanner scanner = new Scanner(System.in);
        double errorThreshold = getErrorThreshold(args, scanner);

        processor.setErrorThreshold(errorThreshold);
        String filePath = getFilePath(scanner, processor);

        try {
            processor.readTradeFile();
            processor.tradesInsertionMaker();
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please try again.");
        } catch (SQLException e) {
            System.err.println("An SQL error occurred: " + e.getMessage());
        } catch (HitReadFileErrorsThresholdException | HitDatabaseInsertErrorsThresholdException e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double getErrorThreshold(String[] args, Scanner scanner) {
        if (args.length == 0) {
            System.err.println("No arguments provided. Please specify 'command' or 'properties'.");
            System.exit(1);
        }

        String option = args[0].toLowerCase();
        switch (option) {
            case "command":
                return getCommandLineThreshold(scanner);
            case "properties":
                return getPropertiesThreshold();
            default:
                System.err.println("Invalid option. Please use 'command' or 'properties'.");
                System.exit(1);
                return 0; // Unreachable, but added to satisfy the compiler
        }
    }

    private static double getCommandLineThreshold(Scanner scanner) {
        while (true) {
            System.out.print("Enter error threshold (1 to 100): ");
            String input = scanner.nextLine();
            try {
                double threshold = Double.parseDouble(input);
                if (threshold >= 1 && threshold <= 100) {
                    return threshold;
                } else {
                    System.out.println("Invalid range. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static double getPropertiesThreshold() {
        Properties properties = new Properties();
        try (var inputStream = TradeRunner.class.getClassLoader().getResourceAsStream("Application.properties")) {
            if (inputStream == null) {
                // Provide an alternative way to load the properties file
                String filePath = "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/Application.properties"; // Adjust this path as needed
                try (var fileInputStream = new FileInputStream(filePath)) {
                    properties.load(fileInputStream);
                }
            } else {
                properties.load(inputStream);
            }

            String thresholdValue = properties.getProperty("error.threshold");
            double threshold = Double.parseDouble(thresholdValue);
            if (threshold < 1 || threshold > 100) {
                throw new InvalidThresholdValueRuntimeException("Threshold must be between 1 and 100.");
            }
            return threshold;
        } catch (Exception e) {
            throw new InvalidThresholdValueRuntimeException("Invalid threshold in properties file: " + e.getMessage());
        }
    }



    private static String getFilePath(Scanner scanner, TradeProcessor processor) {
        while (true) {
            System.out.print("Enter the file path for the trades CSV file: ");
            String filePath = scanner.nextLine();
            processor.setFilePath(filePath);
            try {
                processor.readTradeFile(); // Check if file is valid
                return filePath; // Return if successful
            } catch (FileNotFoundException | HitReadFileErrorsThresholdException e) {
                System.err.println("File not found. Please try again.");
            }
        }
    }
}