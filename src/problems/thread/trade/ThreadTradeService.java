package problems.thread.trade;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("java:S106")
public class ThreadTradeService {
    public static File writeFile = new File("writeLogfile.txt");
    public static File readFile = new File("readLogfile.txt");
    public static boolean isFileExist = false;
    public static String filePath = "";
    public  static int numberOfChunksFiles = 10;
    public static final LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
    public static final LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<>();
    public static final LinkedBlockingQueue<String> queue3 = new LinkedBlockingQueue<>();
    public static final ConcurrentHashMap<String, Integer> queueDistributorConcurrentHashMap = new ConcurrentHashMap<>();


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
