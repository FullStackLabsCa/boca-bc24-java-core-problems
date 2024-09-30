package thread.tradeprocess.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class WriteErrorLogs {

    private WriteErrorLogs() {}

    public static void writeErrorLogsToFile(String errorMessage, String filePath) {
        LocalDateTime now = LocalDateTime.now();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(now + " - " + errorMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("ERROR>> Write error logs." + e.getMessage());
        }
    }
}
