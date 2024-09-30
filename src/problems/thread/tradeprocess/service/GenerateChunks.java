package thread.tradeprocess.service;

import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.concurrent.*;

public class GenerateChunks {
    private GenerateChunks() {}

    public static double getCSVLineCount(String filePath) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)) ){
            reader.readLine();
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println("File not found - " + e.getMessage());
        }
        return lineCount;
    }

    public static void generateFileChunk(String readFilePath, String writeFilePath, int startLine, int endLine) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(writeFilePath)))
        {
            if (startLine == 1) {
                reader.readLine();
            } else {
                for (int i = 0; i < startLine; i++) {
                    reader.readLine();
                }
            }

            String line;
            int counter = startLine;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                counter++;
                if (counter > endLine) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("File not found - " + e.getMessage());
        }
    }

    public static void readCSVFile(HikariDataSource dataSource, String filePath, int numberOfChunks) {
        int totalNumberOfRecordsInChunk = (int) Math.ceil(getCSVLineCount(filePath) / numberOfChunks);
        int startLine = 1;
        int endLine = totalNumberOfRecordsInChunk;
        String baseFilePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/thread/tradeprocess/assets/trade_0";
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            for (int i = 1; i <= numberOfChunks; i++) {
                String chunkFilePath = baseFilePath  + i + ".csv";

                generateFileChunk(filePath, chunkFilePath, startLine, endLine);
                startLine = endLine + 1;
                endLine = (i + 1) * totalNumberOfRecordsInChunk;
                executorService.submit(new ChunkProcessor(dataSource, chunkFilePath));
            }
            executorService.shutdown();
        }
    }
}
