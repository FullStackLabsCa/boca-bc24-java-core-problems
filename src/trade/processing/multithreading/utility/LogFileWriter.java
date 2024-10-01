package trade.processing.multithreading.utility;

import trade.processing.multithreading.exceptions.FileNotExistException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileWriter {

    private LogFileWriter() {
    }

    public static void writeLog(String errorTransaction, String filepath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath, true))) {
            bufferedWriter.write(errorTransaction);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new FileNotExistException("File Not Found"+e.getMessage());
        }
    }
}
