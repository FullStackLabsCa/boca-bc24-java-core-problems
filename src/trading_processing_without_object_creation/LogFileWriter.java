package trading_processing;

import java.io.*;

public class LogFileWriter {

    public static void writeLog(String errorTransaction) {
        try (BufferedWriter bufferedWriter =new BufferedWriter(new FileWriter("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/error_log.txt",true))) {
            bufferedWriter.write(errorTransaction);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
