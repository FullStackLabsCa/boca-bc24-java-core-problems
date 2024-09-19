package problems.tradingPlatform.helpers;

import problems.tradingPlatform.exceptions.HitErrorsThresholdException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorManager {
    static int errorCount = 0;

    public static void checkForErrorThreshold(double errorThreshold,String st,boolean isForRead)
    {
        errorCount++;
        if(isForRead) {
            logError("\n Reading Exception Occurred" + st);
        }else{
            logReadingError("\n Writing Exception Occurred" + st);
        }
        if (errorCount > errorThreshold) {
            try {
                throw new HitErrorsThresholdException();
            } catch (HitErrorsThresholdException e) {
                System.out.println("\n You Increase the Maximum Error Threshold Count : You can't Proceed Further.");
                logError("\n You Increase the Maximum Error Threshold Count : You can't Proceed Further.");
               System.exit(1);
            }
        }
    }

    public static void logError(String errorMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("error_log.txt", true))) {
            writer.write(errorMessage);
            writer.newLine();
        } catch (IOException ioException) {
            System.out.println("Error writing to error file: " + ioException.getMessage());
        }
    }

    public static void logReadingError(String errorMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("errorRead_log.txt", true))) {
            writer.write(errorMessage);
            writer.newLine();
        } catch (IOException ioException) {
            System.out.println("Error Reading to error file: " + ioException.getMessage());
        }
    }
}
