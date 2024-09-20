package trading.PresentationLayer;

import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.databaseConnection.DatabaseConnectivity;
import trading.Utility.FileNotExists;
import trading.Utility.HitErrorsThresholdException;
import trading.Utility.InvalidThresholdValueException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static trading.serviceLayer.TradingService.fetchThresholdValue;
import static trading.serviceLayer.TradingService.readTradingFileAndWriteToQueue;

public class TradingRunner {
    public static HikariDataSource dataSource;
    public static Double thresholdValue = 0.0;

    public static void main(String[] args) throws HitErrorsThresholdException, FileNotExists {
        dataSource = DatabaseConnectivity.configureHikariCP();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trading File with Case1, Case2");
        int i = scanner.nextInt();
        switch (i) {
            case 1:
                case1(scanner);
                break;
            case 2:
                scanner.nextInt();
                case2(scanner);
                break;
            default:
                System.out.println("Enter valid input");
        }
    }
// for user input file and threshold value
    public static void case1(Scanner scanner) {
        String filepath = null;
        while (true) {
            System.out.println("Enter a file path :");
            filepath = scanner.nextLine();
            File file = new File(filepath);
            try {
                if (!file.exists()) {
                    throw new FileNotExists("file not found.....");
                }
                break;
            } catch (FileNotExists e) {
                System.out.println(e.getMessage());
            }
           // break;

        }
        while (true) {
            try {
                System.out.println("Enter threshold value: ");
                thresholdValue = scanner.nextDouble();
                if (thresholdValue < 1 || thresholdValue > 100) {
                    throw new InvalidThresholdValueException("value must be between 1 and 100");
                }

            } catch (InputMismatchException | InvalidThresholdValueException e) {
                System.out.println("Enter valid value..");
                scanner.next();
            }

            try {
                System.out.println(thresholdValue);
                readTradingFileAndWriteToQueue(filepath);
            } catch (HitErrorsThresholdException | IOException | FileNotExists e) {
                System.out.println("Error: File Exceeded threshold value......");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;
        }

    }
// for user input file and taking threshold value from application properties
    private static void case2(Scanner scanner) {
        String filepath = null;
        while (true) {
            System.out.println("Enter a file path :");
            filepath = scanner.nextLine();
            File file = new File(filepath);
            try {
                if (!file.exists()) {
                    throw new FileNotExists("file not found.....");
                }
                break;
            } catch (FileNotExists e) {
                System.out.println(e.getMessage());
            }
        }
        fetchThresholdValue();
        scanner.close();
    }
}
