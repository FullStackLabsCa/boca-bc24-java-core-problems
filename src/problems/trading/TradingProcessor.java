package problems.trading;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.customexceptions.InvalidThresholdRuntimeException;
import problems.trading.customexceptions.InvalidThresholdValueException;
import problems.trading.services.TradingService;
import problems.trading.tradingmodel.TradingValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;


import static problems.trading.databaseconnection.TradingDatabaseConnection.configureHikariCP;
import static problems.trading.services.TradingService.errorThreshold;
import static problems.trading.services.TradingService.setupDBConnectionAndRunFileReading;

public class TradingProcessor {
    public static HikariDataSource dataSource;
    public static double userThreshold;
    public static String filePath;
    public static List<TradingValues> batch = new ArrayList<>();


    //Case 1: getting the threshold from the user:
    public static double getThresholdFromUser() {
        Scanner scanner = new Scanner(System.in);
        double userDefinedErrorThreshold = 0;
        while (true) {
            System.out.println("Enter the threshold you want to set");
            String thresholdInput = scanner.nextLine();
            if (thresholdInput == null || thresholdInput.isEmpty()) {
                System.out.println("Invalid value. Threshold value should be a valid number (between 1 and 100)");
                continue;
            }
                try {
                    //String userDefinedThreshold = scanner.nextLine();
                    userThreshold = Double.parseDouble(thresholdInput);
                    if (userThreshold < 1 || userThreshold > 100) {
                        throw new InvalidThresholdValueException("Please enter a value between 1 and 100 only.");
                    }
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid threshold value. Value should be a number");

                } catch (InvalidThresholdValueException e) {
                    System.out.println("Please enter a value between 1 and 100 only.");
                }
            }

        return userThreshold;
    }


    //Case 2 : Application Properties File for Error Threshold
    public static double getThresholdFromApplicationProperties() {
        double applicationErrorThreshold;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/trading/application.properties"));

            applicationErrorThreshold = Double.parseDouble(properties.getProperty("error.threshold"));
            if (applicationErrorThreshold < 1 || applicationErrorThreshold > 100) {
                throw new InvalidThresholdRuntimeException("The Threshold needs to be between 1 and 100");
            }
            return applicationErrorThreshold;
        } catch (IOException e) {
            throw new RuntimeException("Error loading  the properties file" + e.getMessage());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input" + e.getMessage());
        }
    }


    public static String getFilePath() {
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
        return filePath;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String filePath;
        filePath = getFilePath();
        double defaultErrorThreshold = 25.00;

        System.out.println("Select the case you want to run - Case 1 : Enter your desired threshold value. Case 2: Application Processor");
        int userCaseChoice = scanner.nextInt();
        switch (userCaseChoice){
            case 1:
               defaultErrorThreshold = getThresholdFromUser();
                break;
            case 2:
               defaultErrorThreshold = getThresholdFromApplicationProperties();
                break;
            default:
                System.out.println("Using default value for error threshold: " + defaultErrorThreshold);
        }

            dataSource = configureHikariCP();
            Connection connection = TradingService.connectToDatabase();
            setupDBConnectionAndRunFileReading(connection, filePath, defaultErrorThreshold);

        }
    }






//Case 1: getting the threshold from the user:

//            Scanner userThresholdScanner = new Scanner(System.in);
//            while (true) {
//                System.out.println("Enter the threshold you want to set");
//                try {
//
//                    String threshold = userThresholdScanner.nextLine();
//                    userThreshold = Double.parseDouble(threshold);
//                    if(userThreshold < 1 ||  userThreshold > 100){
//                        throw new InvalidThresholdValueException("Please enter a value between 1 and 100 only.");
//                    }
//                    break;
//
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid threshold value. Value should be a number");
//
//                } catch (InvalidThresholdValueException e) {
//                    System.out.println("Please enter a value between 1 and 100 only.");
//                }
//            }





//        Scanner scanner = new Scanner(System.in);
//        double userDefinedErrorThreshold;
//        while (true) {
//            System.out.println("Enter the error threshold: ");
//            String thresholdInput = scanner.nextLine();
//            if(thresholdInput == null || thresholdInput.isEmpty()){
//                System.out.println("Invalid value. Threshold value should be a valid number (between 1 and 100) ");
//                try{
////                    userDefinedErrorThreshold = Double.parseDouble(thresholdInput);
//                    if( userDefinedErrorThreshold < 1 || userDefinedErrorThreshold > 100){
//                        throw new InvalidThresholdValueException("Threshold should be between 1 and 100 -- Invalid input");
//                    }
//                    break;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Enter a valid number");
//
//                } catch (InvalidThresholdValueException i) {
//                    System.out.println(i.getMessage());
//                }
//            }
//        }
//    return userDefinedErrorThreshold;
//    }
