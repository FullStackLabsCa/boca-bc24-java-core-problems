package trade_processing_multithreading;



import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeChunkGenerator implements ChunkGenerator, FilesWriter{

    int tradesCountInChunkFile = 0;

    @Override
    public boolean generateAndSubmitAllChunks(File file) {
        TradeChunkProcessor.submitToTradeProcessor();
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        DatabaseConnector.configureHikariCP();
        File chunkFile;
        int fileCount;
        int noOfChunk;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             Scanner scanner = new Scanner(new FileReader("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/src/trade_processing_multithreading/chunk_application.properties"))) {
            scanner.useDelimiter("numberOfChunks=");
            noOfChunk = scanner.nextInt();
            fileCount = fileEntriesCounter(file);
            int linesPerChunkFile = fileCount / noOfChunk;
            bufferedReader.readLine(); // to skip first line

            for (int i = 0; i < noOfChunk; i++) {
                chunkFile = new File("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/src/trade_processing_multithreading/trades_chunk/Chunk_trade_" + i);
                while (tradesCountInChunkFile < linesPerChunkFile) {
                    String currentLine = bufferedReader.readLine();
                    if (currentLine != null) {
                        chunkFileWriter(currentLine, chunkFile);
                        tradesCountInChunkFile++;
                    }
                }
                executorService.submit(new TradeChunkProcessor(chunkFile));
//                submitChunk(chunkFile);
                tradesCountInChunkFile = 0;

            }
            executorService.shutdown();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public int fileEntriesCounter(File file) {
        int fileLineCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine(); // To Skip Header Line
            while (bufferedReader.readLine() != null) {
                fileLineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

//    public void submitChunk(File files) {
////        int numberOfThreads = 10;
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//            executorService.submit(new TradeChunkProcessor(files));
//        executorService.shutdown();
//    }
}