package problems.jdbc.tradeprocessor.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

public class TradeProcessorService {

    public void processTrade(String path) {
        try {
            long numOfLines = fileLineCounter(path);
            chunkGenerator(numOfLines, path);
        } catch (IOException e) {
            System.out.println("File parsing failed...");
            ;
        }
    }

    public long fileLineCounter(String path) throws IOException {
        long lineCount;
        try (
                Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8).parallel()) {
            lineCount = stream.count();
        }
        return lineCount - 1;
    }

    public void chunkGenerator(long numOfLines, String path) throws IOException {
        int chunksCount = 10;
        int tempChunkCount = 1;
        String chunkFilePath = "/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src/problems/jdbc" +
                "/tradeprocessor/assets/chunks/trade_records_chunk";
        long tempLineCount = 0;
        long linesCountPerFile = numOfLines / chunksCount;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        reader.readLine();
        BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFilePath + tempChunkCount + ".csv", true));
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
            tempLineCount++;
            if (tempLineCount == linesCountPerFile && tempChunkCount != chunksCount) {
                tempChunkCount++;
                tempLineCount = 0;
                writer.close();
                writer = new BufferedWriter(new FileWriter(chunkFilePath + tempChunkCount + ".csv", true));
            }
        }
        writer.close();
    }

    public void chunkProcessor() throws IOException {
//        ExecutorService executorService = new ThreadPoolExecutor(10);
        String path = "/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src/problems/jdbc/tradeprocessor" +
                "/assets/chunks/";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
        }
    }
}
