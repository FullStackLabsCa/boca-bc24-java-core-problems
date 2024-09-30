package io.reacticestax.tradeprocessingmultithreadingassignment;

import io.reacticestax.tradeprocessingmultithreadingassignment.implementation.ChunkGeneratorImpl;

import java.io.File;
import java.io.IOException;


public class MainRunner {
    public static File file = new File("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/files/trade_records_with_cusip.csv");
    public static void main(String[] args) {
        // Initialize the ChunkGenerator implementation
        ChunkGeneratorImpl chunkGenerator = new ChunkGeneratorImpl();

        try {
            // Count the lines in the file (optional - for display purposes)
            chunkGenerator.countLines(file);
            // Generate and submit chunks for processing
            chunkGenerator.generateChunkAndSubmit(file);

        } catch (IOException e) {
            System.err.println("Error while generating chunks: " + e.getMessage());
            e.printStackTrace();
        }
    }
}