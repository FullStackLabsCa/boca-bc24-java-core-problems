package trade.processing.multithreading.service;


import trade.processing.multithreading.utility.FilesWriter;
import trade.processing.multithreading.config.DatabaseConnector;
import trade.processing.multithreading.exceptions.FileNotExistException;
import trade.processing.multithreading.utility.TaskSubmitter;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeChunkGenerator implements ChunkGenerator, FilesWriter {

    int tradesCountInChunkFile = 0;

    @Override
    public void generateAndSubmitAllChunks(File file) {
        DatabaseConnector.configureHikariCP();
        TaskSubmitter.submitToTradeProcessor();
        TaskSubmitter.submitRetryQue();
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        File chunkFile;
        int fileCount;
        int noOfChunk;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             Scanner scanner = new Scanner(new FileReader("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/src/trade/processing/multithreading/chunk_application.properties"))) {
            scanner.useDelimiter("numberOfChunks=");
            noOfChunk = scanner.nextInt();
            fileCount = fileEntriesCounter(file);
            int linesPerChunkFile = fileCount / noOfChunk;
            bufferedReader.readLine(); // to skip first line

            for (int i = 0; i < noOfChunk; i++) {
                chunkFile = new File("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/src/trade/processing/multithreading/trades_chunk/Chunk_trade_" + i);
                while (tradesCountInChunkFile < linesPerChunkFile) {
                    String currentLine = bufferedReader.readLine();
                    if (currentLine != null) {
                        chunkFileWriter(currentLine, chunkFile);
                        tradesCountInChunkFile++;
                    }
                }
                executorService.submit(new TradeChunkProcessor(chunkFile));
                tradesCountInChunkFile = 0;

            }
            executorService.shutdown();

        } catch (IOException e) {
            throw new FileNotExistException(" File Not Found "+e.getMessage());
        }
    }
    public int fileEntriesCounter(File file) {
        int fileLineCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine(); // To Skip Header Line
            while (bufferedReader.readLine() != null) {
                fileLineCount++;
            }
        } catch (IOException e) {
            throw new FileNotExistException("File Not Found"+e.getMessage());
        }
        return fileLineCount;
    }

    @Override
    public void chunkFileWriter(String fileLine, File filepath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath, true))) {
            bufferedWriter.write(fileLine);
            if (tradesCountInChunkFile < 999) {
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new FileNotExistException("File Not Found"+e.getMessage());
        }
    }
}