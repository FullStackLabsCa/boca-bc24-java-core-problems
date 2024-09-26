package problems.thread.trade;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("java:S106")
public class ChunkFileGenerator {

    public ChunkFileGenerator() {
        chunkGenerator(ThreadTradeService.filePath);
    }

    private void chunkGenerator(String filePath) {
        ArrayList<String> tradeChunkArrayList = new ArrayList<>();
        // Create a single-threaded executor to read the transactions
        try (ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor()) {

            // Simulate reading from a file
            singleThreadExecutor.submit(() -> {
                int numberOfChunksFiles = 10;
                long startLine = 0;
                int numberOfRowsInPerChunk;
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    String headingRow = reader.readLine();

                    //getting the totalNumberOfRowsFromMainFile
                    while ((line = reader.readLine()) != null) {
                        tradeChunkArrayList.add(line);
                    }

                    numberOfRowsInPerChunk = (int) Math.round(Double.parseDouble(String.valueOf(tradeChunkArrayList.size())) / numberOfChunksFiles);

                    //creating chunkFiles
                    for (int i = 0; i < numberOfChunksFiles; i++) {
                        String fileName = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/src/problems/thread/trade/rawdata/trade_chunk_" + (i + 1) + ".csv";
                        //writing to file
                        startLine = writeToChunkFiles(fileName, headingRow, startLine, numberOfRowsInPerChunk, tradeChunkArrayList);
                    }

                    System.out.println(ThreadTradeService.ANSI_GREEN + ">>>>>>Data are written in the chunk file successfully<<<<<");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static long writeToChunkFiles(String fileName, String headingRow, long startLine, int numberOfRowsInPerChunk, ArrayList<String> tradeChunkArrayList) {
        long endLine;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(headingRow);
            writer.newLine();
            endLine = Math.min(startLine + numberOfRowsInPerChunk, tradeChunkArrayList.size());

            for (int j = (int) startLine; j < endLine; j++) {
                writer.write(tradeChunkArrayList.get(j));
                if (j < endLine - 1) {
                    writer.newLine();
                }
            }
            startLine = endLine;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return startLine;
    }
}
