package trading.PresentationLayer;

import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.databaseConnection.DatabaseConnectivity;
import trading.Utility.HitErrorsThresholdException;
import trading.Utility.InvalidThresholdValueException;

import java.util.InputMismatchException;
import java.util.Scanner;

import static trading.serviceLayer.TradingService.readTradingFileAndWriteToQueue;

public class TradingRunner {
    public static HikariDataSource dataSource;
    public static Double thresholdValue = 0.0;

    public static void main(String[] args) throws HitErrorsThresholdException {
        dataSource = DatabaseConnectivity.configureHikariCP();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a file path");
        String filePath = scanner.nextLine();
        System.out.println("Enter a  Threshold  value :");
        while (true) {
            try {
                thresholdValue = scanner.nextDouble();
                if (thresholdValue < 1 || thresholdValue > 100) {
                    throw new InvalidThresholdValueException("value must be between 1 and 100");
                }
                break;
            } catch (InputMismatchException | InvalidThresholdValueException e) {
                System.out.println("Enter valid value..");
                scanner.next();
            }
        }
        try {
            System.out.println(thresholdValue);
            readTradingFileAndWriteToQueue(filePath);
        } catch (HitErrorsThresholdException e) {
            System.out.println("Error: File Exceeded threshold value......");
        }
        scanner.close();
    }
}
