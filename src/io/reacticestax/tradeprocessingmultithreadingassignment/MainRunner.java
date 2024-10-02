package io.reacticestax.tradeprocessingmultithreadingassignment;

import io.reacticestax.tradeprocessingmultithreadingassignment.implementation.ChunkGeneratorImpl;
import io.reacticestax.tradeprocessingmultithreadingassignment.implementation.ChunkProcessorImpl;
import io.reacticestax.tradeprocessingmultithreadingassignment.implementation.TradeProcessor;

import java.io.IOException;

import static io.reacticestax.tradeprocessingmultithreadingassignment.implementation.ChunkProcessorImpl.chunkFilePath;

public class MainRunner {
    public static void main(String[] args) {
        final Thread tradeProcessorThread = new Thread(new TradeProcessor());
        tradeProcessorThread.start();
        ChunkGeneratorImpl buildChunk = new ChunkGeneratorImpl();
       // ChunkProcessorImpl processChunk = new ChunkProcessorImpl(chunkFilePath);
        try {
            // Count lines and generate chunks
            buildChunk.countLines(ChunkGeneratorImpl.file);
            buildChunk.generateChunkAndSubmit(ChunkGeneratorImpl.file);
//            processChunk.processChunk(chunkFilePath);

            for (int i = 1; i <= 10; i++) {
                chunkFilePath = ChunkGeneratorImpl.CHUNK_DIR + "file_chunk_0" + i + ".csv";

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("MainRunner: came out of sleep");
        try {
            tradeProcessorThread.join();
            System.out.println("tradeProcessing FINISHED..........................................");
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}