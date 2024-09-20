package trading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    static double userThreshold = 0;

    public static double thresholdFromCommandLine(Scanner scanner) throws InvalidThresholdValueException {
        System.out.println("Enter the Threshold Value");
        try {
            userThreshold = scanner.nextDouble();
            scanner.nextLine();
            if (userThreshold > 100 && userThreshold < 0) {
                throw new InvalidThresholdValueException("Invalid input Please enter a value in between 0 and 100...");
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new InvalidThresholdValueException("Invalid input Please Try again...");
        }
        return userThreshold;
    }

    public static double thresholdFromApplicationFile(Scanner scanner) throws InvalidThresholdValueRuntimeException {
        double userThreshold = 0;
        File file = new File("application.properties");
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

    public static void userInteractionMethod() {

        boolean isvalid = false;
        try (Scanner scanner = new Scanner(System.in);) {
            while (!isvalid) {
                try {
                    trading.DatabaseConnector.configureHikariCP();
//                    userThreshold =  thresholdFromApplicationFile(scanner);
                    userThreshold = thresholdFromCommandLine(scanner);

                    System.out.println("Provide the File Path");
                    String filePath = scanner.nextLine();
                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new FileNotFoundException("File Doesn't Exist");
                    }
                    isvalid = true;
                    CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
                    List<Trade> trades = csvTradeFileReader.tradeFileReader(file);
                    SQlTradeDAO sQlTradeDAO = new SQlTradeDAO();
                    sQlTradeDAO.tradeFilewriter(trades);

                } catch (FileNotFoundException | InvalidThresholdValueException e) {
                    System.out.println(e.getMessage());
                    isvalid = false;
                }
            }
        }
    }
}
