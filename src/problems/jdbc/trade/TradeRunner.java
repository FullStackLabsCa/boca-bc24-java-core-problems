package jdbc.trade;

import jdbc.trade.exceptions.InvalidThresholdValueException;
import jdbc.trade.exceptions.InvalidThresholdValueRuntimeException;
import jdbc.trade.service.TradeService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class TradeRunner {

    // Get file path from user
    public static String getFilePathFromUser () {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        while (true) {
            System.out.println("Please enter valid file path");
            filePath = scanner.nextLine();
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                System.out.println("file doesn't exists");
                continue;
            }
            break;
        }
        return filePath;
    }

    // Get threshold value from user input
    public static double getThresholdValueFromUser () {
        Scanner scanner = new Scanner(System.in);
        double errorThreshold;
        while (true) {
            System.out.println("Enter error threshold::");
            String inputThreshold = scanner.nextLine();
            if (inputThreshold == null || inputThreshold.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid decimal value.");
                continue;
            }
            try {
                errorThreshold = Double.parseDouble(inputThreshold);
                if (errorThreshold < 1 || errorThreshold > 100) {
                    throw new InvalidThresholdValueException("Please enter threshold value in a range 1-100");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal value.");
            } catch (InvalidThresholdValueException e) {
                System.out.println(e.getMessage());
            }
        }
        return errorThreshold;
    }

    // Get threshold value from application properties
    public static double getThresholdFromApplicationProperties () {
        double errorThreshold = 0;
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/resources/application.properties")) {
            properties.load(reader);

            errorThreshold = Double.parseDouble(properties.getProperty("error.threshold"));
            if (errorThreshold < 1 || errorThreshold > 100) {
                throw new InvalidThresholdValueRuntimeException("Please fix the application properties it should be double and within a range 1-100");
            }
        } catch (IOException e) {
            System.out.println("Error loading properties file." + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again with a valid decimal value." + e.getMessage());
        }
        return errorThreshold;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        double errorThreshold = 25;

       filePath = getFilePathFromUser();

        System.out.println("How you wanna take error threshold::\n1. User Input\n2. Application Properties \ndefault. 25%");

        try {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    errorThreshold = getThresholdValueFromUser();
                    break;
                case 2:
                    errorThreshold = getThresholdFromApplicationProperties();
                    break;
                default:
                    System.out.println("Processing with default threshold " + errorThreshold);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number input");
        }
        TradeService.setupDbConnectionAndReadFile(filePath, errorThreshold);
    }
}

// /Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/trades.csv