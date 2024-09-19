package problems.tradeOperations.manager;

import problems.tradeOperations.exceptionFiles.InvalidThresholdValueException;
import problems.tradeOperations.exceptionFiles.InvalidThresholdValueRuntimeException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ThresholdManager {

    private double errorThreshold;

    public ThresholdManager(String[] args) throws InvalidThresholdValueException {
        if (args.length > 0 && !args[0].trim().isEmpty()) {
            this.errorThreshold = getThresholdFromCommandLine(args);
        } else {
            // No command-line argument provided; fall back to properties
            this.errorThreshold = getThresholdFromProperties();
        }
    }

    // New constructor to directly set the threshold
    public ThresholdManager(double threshold) throws InvalidThresholdValueException {
        if (threshold < 1 || threshold > 100) {
            throw new InvalidThresholdValueException("Threshold must be between 1 and 100.");
        }
        this.errorThreshold = threshold;
    }

    private double getThresholdFromCommandLine(String[] args) throws InvalidThresholdValueException {
        try {
            double threshold = Double.parseDouble(args[0]);
            if (threshold < 1 || threshold > 100) {
                throw new InvalidThresholdValueException("Threshold must be between 1 and 100.");
            }
            return threshold;
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueException("Invalid threshold value provided.");
        }
    }

    private double getThresholdFromProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/problems/tradeOperations/extraUsedFiles/application.properties")) {
            properties.load(input);
            String thresholdValue = properties.getProperty("error.threshold");
            double threshold = Double.parseDouble(thresholdValue);
            if (threshold < 1 || threshold > 100) {
                throw new InvalidThresholdValueRuntimeException("Threshold in properties file must be between 1 and 100.");
            }
            return threshold;
        } catch (IOException e) {
            throw new InvalidThresholdValueRuntimeException("Error reading properties file: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidThresholdValueRuntimeException("Invalid threshold value in properties file.");
        }
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }

    public double promptForThreshold() {
        // This method is not needed anymore, as we handle input in the Main class
        return errorThreshold; // Just a placeholder
    }
}






//package problems.tradeOperations;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.Scanner;
//
//public class ThresholdManager {
//
//    private double errorThreshold;
//
//    public ThresholdManager(String[] args) throws InvalidThresholdValueException {
//        if (args.length > 0) {
//            this.errorThreshold = getThresholdFromCommandLine(args);
//        } else {
//            // No command-line argument provided; fall back to properties
//            this.errorThreshold = getThresholdFromProperties();
//        }
//    }
//
//    private double getThresholdFromCommandLine(String[] args) throws InvalidThresholdValueException {
//        try {
//            double threshold = Double.parseDouble(args[0]);
//            if (threshold < 1 || threshold > 100) {
//                throw new InvalidThresholdValueException("Threshold must be between 1 and 100.");
//            }
//            return threshold;
//        } catch (NumberFormatException e) {
//            throw new InvalidThresholdValueException("Invalid threshold value provided.");
//        }
//    }
//
//    private double getThresholdFromProperties() {
//        Properties properties = new Properties();
//        try (FileInputStream input = new FileInputStream("src/problems/tradeOperations/application.properties")) {
//            properties.load(input);
//            String thresholdValue = properties.getProperty("error.threshold");
//            double threshold = Double.parseDouble(thresholdValue);
//            if (threshold < 1 || threshold > 100) {
//                throw new InvalidThresholdValueRuntimeException("Threshold in properties file must be between 1 and 100.");
//            }
//            return threshold;
//        } catch (IOException e) {
//            throw new InvalidThresholdValueRuntimeException("Error reading properties file: " + e.getMessage());
//        } catch (NumberFormatException e) {
//            throw new InvalidThresholdValueRuntimeException("Invalid threshold value in properties file.");
//        }
//    }
//
//    public double getErrorThreshold() {
//        return errorThreshold;
//    }
//
//    public double promptForThreshold() {
//        Scanner scanner = new Scanner(System.in);
//        double threshold = 0;
//
//        while (true) {
//            System.out.print("Please enter a valid error threshold (1-100): ");
//            String input = scanner.nextLine();
//            try {
//                threshold = Double.parseDouble(input);
//                if (threshold >= 1 && threshold <= 100) {
//                    break; // Valid threshold
//                }
//                System.out.println("Threshold must be between 1 and 100.");
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//            }
//        }
//        return threshold;
//    }
//}
