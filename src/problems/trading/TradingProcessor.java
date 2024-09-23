package problems.trading;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.repository.TradingRepository;
import problems.trading.services.TradingService;
import problems.trading.tradingmodel.TradingValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import static problems.trading.databaseconnection.TradingDatabaseConnection.configureHikariCP;
import static problems.trading.repository.TradingRepository.prepareStatements;
import static problems.trading.services.TradingService.readTradingFileAndWriteToFile;
import static problems.trading.services.TradingService.setupDBConnectionAndRunFileReading;

public class TradingProcessor {
    public static HikariDataSource dataSource;
    public static double userThreshold;
    public static String filePath;
    public static List<TradingValues> batch = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the file path");
            filePath = scanner.nextLine();
            File file = new File(filePath);
            if(file.exists()) {
                break;
            } else {
                System.out.println("Invalid path provided. Please enter the valid file path");
            }

        }

            //}

            //Case 1: getting the threshold from the user:
            Scanner userThresholdScanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter the threshold you want to set");
                try {
                    userThreshold = userThresholdScanner.nextDouble();
                    userThreshold = DataValidation.checkForValidThresholdFromUser(userThreshold);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid threshold value. Value should be between 1 to 100.");
                    userThresholdScanner.next();
                    //break;
                }
            }
            // File tradingFile = new File(filePath);
            dataSource = TradingDatabaseConnection.configureHikariCP();
            Connection connection = TradingService.connectToDatabase();
            setupDBConnectionAndRunFileReading(connection, filePath);

        }
    }

