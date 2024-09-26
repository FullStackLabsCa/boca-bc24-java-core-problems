package fileiotradeassignment;

import fileiotradeassignment.customExceptionClasses.HitDatabaseInsertErrorsThresholdException;
import fileiotradeassignment.customExceptionClasses.HitReadFileErrorsThresholdException;
import fileiotradeassignment.customExceptionClasses.InvalidThresholdValueRuntimeException;
import fileiotradeassignment.repository.TradeRepository;
import fileiotradeassignment.service.TradeService;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;



public class TradeRunner {


    public static void main(String[] args) {
        TradeService service = new TradeService();
        TradeRepository repo = new TradeRepository();


        Scanner scanner = new Scanner(System.in);
        String filePath = getFilePath(scanner, service);
        double errorThreshold = selectModeAndGetThreshold(scanner, filePath);

        service.setErrorThreshold(errorThreshold);

        try {
            service.readTradeFile();
            repo.tradesInsertionMaker(service);
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

    private static double selectModeAndGetThreshold(Scanner scanner, String filePath) {
        while (true) {
            System.out.println("Select mode: 'command' or 'properties'");
            String option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "command":
                    return getCommandLineThreshold(scanner);
                case "properties":
                    return getPropertiesThreshold();
                default:
                    System.err.println("Invalid option. Please use 'command' or 'properties'.");
            }
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

        try ( FileReader reader = new FileReader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/fileiotradeassignment/resources/Application.properties")) {
            Properties properties = new Properties();
            properties.load(reader);



            String thresholdValue = properties.getProperty("error.threshold");
            double threshold = Double.parseDouble(thresholdValue);

            if (threshold < 1 || threshold > 100) {
                throw new InvalidThresholdValueRuntimeException("Threshold must be between 1 and 100.");
            }
            return threshold;
        } catch (Exception e) {
            throw new InvalidThresholdValueRuntimeException("Error loading properties: " + e.getMessage());
        }
    }



    private static String getFilePath(Scanner scanner, TradeService service) {
        while (true) {
            System.out.print("Enter the file path for the trades CSV file: ");
            String filePath = scanner.nextLine();
            service.setFilePath(filePath);
            return filePath;
        }
    }
}