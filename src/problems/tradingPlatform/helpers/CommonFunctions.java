package problems.tradingPlatform.helpers;

import problems.tradingPlatform.exceptions.InvalidThresholdValueException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Properties;

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
