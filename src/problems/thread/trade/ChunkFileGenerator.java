package problems.thread.trade;

import java.io.*;
import java.util.ArrayList;

public class ChunkFileGenerator {
    public static void chunkGenerator(String filePath) {
        ArrayList<String> tradeChunkArrayList = new ArrayList<>();
        int numberOfChunksFiles = 10;
        long startLine = 0;
        long endLine = 0;
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
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write(headingRow);
                    writer.newLine();
                    endLine = Math.min(startLine + numberOfRowsInPerChunk, tradeChunkArrayList.size());
                    for (int j = (int) startLine; j < endLine; j++) {
                        writer.write(tradeChunkArrayList.get(j));
                        writer.newLine();
                    }
                    startLine = endLine;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Data are written in the chunk file....");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
