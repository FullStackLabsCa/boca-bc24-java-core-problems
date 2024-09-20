package problems.tradingPlatform.helpers;

import problems.tradingPlatform.databasehelpers.DatabaseOperations;
import problems.tradingPlatform.exceptions.HitErrorsThresholdException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorManager {
    public static int readErrorCount = 0;
    public static int writeErrorCount = 0;
    public static double errorThreshold = 25;
    public  static double totalRows ;

    public static void checkForErrorThreshold(String st, boolean isForRead) {
        checkForErrorThreshold(st, isForRead, 0);
    }

    public static void checkForErrorThreshold( String st, boolean isForRead, int row)
    {
        errorThreshold = DatabaseOperations.errorThresholdCount;
        totalRows = DatabaseOperations.totalRowsOfFile;
        if(isForRead) {
            readErrorCount++;
            logReadingError("\n Reading Exception Occurred In Row " + row + "Message : " + st,true);
        }else{
            writeErrorCount++;
            logWritingError("\n Writing Exception Occurred In Row "+ row + "Message : " + st,true);
        }
        if (readErrorCount > errorThreshold || writeErrorCount > errorThreshold) {
            try {
                throw new HitErrorsThresholdException();
            } catch (HitErrorsThresholdException e) {
                if(isForRead) {
                    System.out.println("\nYou Increase the Maximum Error Threshold Count While Reading : You can't Proceed Further.");
                    System.out.println("Total Successful Rows While Reading : " + (totalRows - readErrorCount));
                    System.out.println("Total Error Rows While Reading : " + readErrorCount);
                    logReadingError("\nYou Increase the Maximum Error Threshold Count While Reading: the Files :  You can't Proceed Further.",true);
                }else{
                    System.out.println("\nYou Increase the Maximum Error Threshold Count While Writing : You can't Proceed Further.");
                    System.out.println("Total Successful Rows While Writing : " + (totalRows - writeErrorCount));
                    System.out.println("Total Error Rows While Writing : " + writeErrorCount);
                    logWritingError("\nYou Increase the Maximum Error Threshold Count While Writing the Files : You can't Proceed Further.",true);
                }
               System.exit(1);
            }
        }
    }

    public static void logReadingError(String errorMessage,boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("errorRead_log.txt", append))) {
            writer.write(errorMessage);
            writer.newLine();
        } catch (IOException ioException) {
            System.out.println("Error writing to error file: " + ioException.getMessage());
        }
    }

    public static void logWritingError(String errorMessage,boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("errorWrite_log.txt", append))) {
            writer.write(errorMessage);
            writer.newLine();
        } catch (IOException ioException) {
            System.out.println("Error Reading to error file: " + ioException.getMessage());
        }
    }
}
