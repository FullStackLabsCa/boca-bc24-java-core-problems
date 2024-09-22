package jdbc.trade;

import jdbc.trade.exceptions.InvalidThresholdValueException;
import jdbc.trade.exceptions.InvalidThresholdValueRuntimeException;
import jdbc.trade.service.TradeService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        double errorThreshold;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/resources/application.properties"));

            errorThreshold = Double.parseDouble(properties.getProperty("error.threshold"));
            if (errorThreshold < 1 || errorThreshold > 100) {
                throw new InvalidThresholdValueRuntimeException("Please fix the application properties it should be double and within a range 1-100");
            }
            return errorThreshold;
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input. Please try again with a valid decimal value.", e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        double ERROR_THRESHOLD = 25;

       filePath = getFilePathFromUser();

        System.out.println("How you wanna determine threshold::\n " +
                "1. User Input\n 2. Application Properties \n default. 25%");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                ERROR_THRESHOLD = getThresholdValueFromUser();
                break;
            case 2:
                ERROR_THRESHOLD = getThresholdFromApplicationProperties();
                break;
            default:
                System.out.println("Processing with default threshold " + ERROR_THRESHOLD);
                break;
        }

        TradeService.setupDbConnectionAndReadFile(filePath, ERROR_THRESHOLD);
    }
}
