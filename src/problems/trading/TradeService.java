package problems.trading;

import problems.trading.exceptions.HitErrorsThresholdException;

import java.io.File;
import java.util.ArrayList;

public class TradeService {
    public static int writeErrorCount = 0;
    public static int errorCount = 0;
    public static double errorThreshold = 0;
    public static File writeFile = new File("writeLogfile.txt");
    public static File readFile = new File("readLogfile.txt");
    public static boolean isFileExist = false;
    public static boolean isThresholdValid = false;
    public static String filePath = "";
    public static ArrayList<TradeTransaction> tradingTransactionArrayList = new ArrayList<>(5000);


    public static void checkingThreshold(ArrayList<TradeTransaction> tradingTransactionArrayList) {
        if (errorCount > (errorThreshold * tradingTransactionArrayList.size()) / 100) {
            throw new HitErrorsThresholdException("Errors exceeded threshold limit");
        }
    }

    public static void checkWriteLogFileExistOrNot() {
        if (writeFile.exists()) {
            if (writeFile.delete()) {
                System.out.println("Write Log file deleted successfully.");
            } else {
                System.out.println("Failed to delete the write log file.");
            }
        } else {
            System.out.println("Write Log file does not exist.");
        }
    }

    public static void checkReadLogFileExistOrNot() {
        if (readFile.exists()) {
            if (readFile.delete()) {
                System.out.println("Read Log file deleted successfully.");
            } else {
                System.out.println("Failed to delete the read log file.");
            }
        } else {
            System.out.println("Read Log file does not exist.");
        }
    }
}
