package trading.utility;

import trading.DAO.SQlTradeDAO;
import trading.config.DatabaseConnector;
import trading.exceptions.InvalidThresholdValueException;
import trading.exceptions.InvalidThresholdValueRuntimeException;
import trading.model.Trade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    public static double userThreshold = 0;

    public static boolean isValidThreshold(String inputThreshold) throws InvalidThresholdValueException {
        try {
            userThreshold = Double.parseDouble(inputThreshold);
            if (userThreshold > 100 && userThreshold < 0) {
                throw new InvalidThresholdValueException("Invalid input Please enter a value in between 0 and 100...");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            throw new InvalidThresholdValueException("Invalid input Please Try again...");
        }
        return true;
    }

    public static double thresholdFromApplicationFile() throws InvalidThresholdValueRuntimeException {
        File file = new File("application.properties");
        Scanner scanner =new Scanner(System.in);
        scanner.useDelimiter("error.threshold=");
        userThreshold = scanner.nextDouble();
        if (userThreshold > 100) {
            throw new InvalidThresholdValueRuntimeException("Invalid Input Existing....");
        }
        if (!file.exists()) {
            throw new InvalidThresholdValueRuntimeException("Invalid Input Existing....");
        }
        return userThreshold;
    }

    public static File fileFromCommandLine(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File Doesn't Exist");
        }
        return file;
    }

    public static void userInteractionMethod() {
        boolean isvalid = false;
        try (Scanner scanner = new Scanner(System.in);) {
            while (!isvalid) {
                try {
                    DatabaseConnector.configureHikariCP();
                    System.out.println("Enter the Threshold Value");
                    String inputThreshold = scanner.nextLine();
                    scanner.nextLine();
//                    userThreshold =  thresholdFromApplicationFile();
                    isValidThreshold(inputThreshold);
                    System.out.println("Provide the File Path");
                    String filePath = scanner.nextLine();
                    File file = fileFromCommandLine(filePath);
                    isvalid = true;
                    CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
                    List<Trade> trades = csvTradeFileReader.tradeFileReader(file);
                    SQlTradeDAO sQlTradeDAO = new SQlTradeDAO();
                    sQlTradeDAO.tradeFilewriter(trades);
                } catch (FileNotFoundException | InvalidThresholdValueException e) {
                    scanner.nextLine();
                    System.out.println(e.getMessage());
                    isvalid = false;
                }
            }
        }
    }
}
