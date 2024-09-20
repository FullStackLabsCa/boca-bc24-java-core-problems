package problems.tradingPlatform.helpers;

import problems.tradingPlatform.exceptions.InvalidThresholdValueException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import static problems.tradingPlatform.helpers.ErrorManager.logReadingError;
import static problems.tradingPlatform.helpers.ErrorManager.logWritingError;

public class CommonFunctions {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date convertStringToDate(String strDate)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return  convertFromJAVADateToSQLDate(sdf.parse(strDate)) ;
        } catch (ParseException e) {
            System.out.println("Exception Occurred in date conversion : ");
            return null; // Return null if parsing fails
        }
    }


    public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new java.sql.Date(javaDate.getTime());
        }
        return sqlDate;
    }

    public static double askUserForInput(Scanner scanner) {
        int choice = -1;
        double percentage = -1;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Use Percentage from Application Properties");
            System.out.println("2. You want to add a Percentage Threshold");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Please enter a valid input.");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    percentage = CommonFunctions.getDataFromApplicationProperties();
                    // System.out.println("Percentage from application properties: " + percentage);
                    break;
                case 2:
                    percentage = getPercentageThreshold(scanner);
                    // System.out.println("User-defined percentage threshold: " + percentage);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 1 && choice != 2);

        return percentage;
    }

    public static int getPercentageThreshold(Scanner scanner) {
        int percentage = -1;
        while (percentage < 0 || percentage > 100) {
            System.out.print("Please Enter the Error Threshold Percentage (0-100): ");
            try {
                percentage = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer value ");
                scanner.next();
                continue;
            }

            if (percentage < 0 || percentage > 100) {
                System.out.println("Invalid input. Please enter a percentage between 0 and 100.");
            }
        }
        return  percentage;
    }

    public static long getTotalRowsCount(File file) {
        long totalRows = -1;
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNext()) {
                scanner.nextLine();
                totalRows++;
            }
            return totalRows;
        } catch (IOException e) {
            ErrorManager.checkForErrorThreshold("\n Failed to open File : getTotalRowsCount ",true);
        }
        return totalRows;
    }


    public static double getDataFromApplicationProperties()
    {
        Properties properties = new Properties();
        double threshold = 0.25;
        try (FileInputStream input = new FileInputStream("/Users/Parth.Shah/Documents/Projects/boca-bc24-java-core-problems/src/problems/application.properties")) {
            properties.load(input);
            String thresholdValue = properties.getProperty("error.threshold");
             threshold = Double.parseDouble(thresholdValue);
             if(threshold >0 && threshold<100)
             {
                 return threshold;
             }else{
                 System.out.println("Please enter a valid integer value in Application Properties then load again ");
                 try {
                     throw new InvalidThresholdValueException();
                 } catch (InvalidThresholdValueException ex) {
                     System.exit(1);
                 }
             }

            System.out.println("Error Threshold: " + threshold);
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
        } catch (NumberFormatException  | InputMismatchException e) {
            System.out.println("Please enter a valid integer value in Application Properties then load again ");
            try {
                throw new InvalidThresholdValueException();
            } catch (InvalidThresholdValueException ex) {
                System.exit(1);
            }
        }
        return threshold;
    }








}
