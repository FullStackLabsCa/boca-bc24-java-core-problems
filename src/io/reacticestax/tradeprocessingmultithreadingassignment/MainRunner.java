package io.reacticestax.tradeprocessingmultithreadingassignment;

import io.reacticestax.tradeprocessingmultithreadingassignment.implementation.ChunkGeneratorImpl;
import io.reacticestax.tradeprocessingmultithreadingassignment.implementation.TradeProcessingServiceImpl;

import java.io.File;
import java.io.IOException;

import static io.reacticestax.tradeprocessingmultithreadingassignment.implementation.ChunkGeneratorImpl.file;

public class MainRunner {
    public static void main(String[] args) {
        ChunkGeneratorImpl buildChunk = new ChunkGeneratorImpl(new ConfigLoader(file.getPath()));
        TradeProcessingServiceImpl tradeProcessingService = new TradeProcessingServiceImpl();

        try {
            // Count lines and generate chunks
            buildChunk.countLines(ChunkGeneratorImpl.file);
            buildChunk.generateChunkAndSubmit(ChunkGeneratorImpl.file);

            // Assuming you know the chunk directory and it has been created
            String chunkDirectory = "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/files/chunks/";

            // Process each chunk file
            for (int i = 1; i <= 10; i++) {
                String chunkFilePath = chunkDirectory + "file_chunk_0" + i + ".csv"; // Construct file path
                File chunkFile = new File(chunkFilePath);

                // Check if the chunk file exists before processing
                if (chunkFile.exists()) {
                    tradeProcessingService.saveToRawTableInDB(chunkFilePath);
                } else {
                    System.out.println("Chunk file " + chunkFilePath + " does not exist.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}