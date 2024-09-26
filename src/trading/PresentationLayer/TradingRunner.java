package trading.PresentationLayer;
import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.databaseConnection.DatabaseConnectivity;
import trading.Utility.FileNotExists;
import trading.Utility.HitErrorsThresholdException;
import trading.Utility.InvalidThresholdValueException;
import trading.serviceLayer.TradingService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static trading.serviceLayer.TradingService.fetchThresholdValue;
import static trading.serviceLayer.TradingService.readTradingFileAndWriteToQueue;

public class TradingRunner {
    public static HikariDataSource dataSource;
    public static Double thresholdValue =0.0 ;

    public static void main(String[] args) throws HitErrorsThresholdException, FileNotExists {
        dataSource = DatabaseConnectivity.configureHikariCP();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trading File with:- Case1, Case2");
        System.out.println("Type '1' for case1 or Type '2' for  Case2");
            try{
                int input = scanner.nextInt();
                scanner.nextLine();
                switch (input) {
                    case 1:
                        case1(scanner);
                        break;
                    case 2:
                        case2(scanner);
                        break;
                    default:
                        throw  new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("enter only 1 or 2");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException | InvalidThresholdValueException e) {
                throw new RuntimeException(e);
            }

    }

    public static void case1(Scanner scanner) throws FileNotExists, SQLException, HitErrorsThresholdException, IOException {
        String filepath = "";

        while (true) {
            System.out.println("Enter a file path:");
            filepath = scanner.nextLine();
            File file = new File(filepath);
            if (file.exists()) {
                break; // Exit the loop if the file exists
            } else {
                // Throw exception instead of just printing an error
                System.out.println("file not found.....");
            }
        }
        double thresholdValue=0;
        while (true) {
            try {
                System.out.println("Enter threshold value (1-100): ");
                 thresholdValue = scanner.nextDouble();
                if (thresholdValue < 1 && thresholdValue > 100) {
                    throw new InvalidThresholdValueException("Value must be between 1 and 100.");
                }
                TradingRunner.thresholdValue = thresholdValue; // Update the static variable
                break;
            } catch (InvalidThresholdValueException | InputMismatchException e) {
                System.out.println("\"Value must be between 1 and 100.\"Enter again");
                break;
            }
        }
                try {
            System.out.println(thresholdValue);
            readTradingFileAndWriteToQueue(filepath);
        } catch (HitErrorsThresholdException | IOException | FileNotExists e) {
            System.out.println("Error: File Exceeded threshold value......");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void case2(Scanner scanner) throws InvalidThresholdValueException, SQLException, HitErrorsThresholdException, IOException {
        String filepath = "";

        while (true) {
            System.out.println("Enter a file path:");
            filepath = scanner.nextLine();
            File file = new File(filepath);
            if (!file.exists()) {
                System.out.println("File not found. Please try again.");
                break;
            } else {
                break;
            }
        }
        fetchThresholdValue(); // Fetch the threshold value after confirming the file path
        System.out.println("Threshold value fetched: " + thresholdValue);
        readTradingFileAndWriteToQueue(filepath);
    }
}
