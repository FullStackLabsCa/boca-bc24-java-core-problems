package trading.PresentationLayer;

import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.databaseConnection.DatabaseConnectivity;
import trading.Utility.FileNotExists;
import trading.Utility.HitErrorsThresholdException;
import trading.Utility.InvalidThresholdValueException;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static trading.serviceLayer.TradingService.readTradingFileAndWriteToQueue;

public class TradingRunner {
    public static HikariDataSource dataSource;
    public static Double thresholdValue = 0.0;

    public static void main(String[] args) throws HitErrorsThresholdException, FileNotExists {
        dataSource = DatabaseConnectivity.configureHikariCP();
        Scanner scanner = new Scanner(System.in);
        String filepath = null;
        while (true){
            System.out.println("Enter a file path :");
            filepath = scanner.nextLine();
            File file = new File(filepath);
            if (!file.exists()) {
                System.out.println("Enter valid file path.");
                continue;
            }
            break;
        }
        while (true) {
            try {
                System.out.println("Enter threshold value: ");
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
            readTradingFileAndWriteToQueue(filepath);
        } catch (HitErrorsThresholdException | IOException e) {
            System.out.println("Error: File Exceeded threshold value......");
        }
    }
}
