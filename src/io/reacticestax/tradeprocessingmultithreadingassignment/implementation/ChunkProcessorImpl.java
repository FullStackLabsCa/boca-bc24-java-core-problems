package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.ChunkProcessor;

import java.io.*;

public class ChunkProcessorImpl implements ChunkProcessor, Runnable {
    private final String chunkFilePath;

    // Constructor to accept the file path of the chunk to process
    public ChunkProcessorImpl(String chunkFilePath) {
        this.chunkFilePath = chunkFilePath;
    }

    // This is the method to process a chunk of data
    @Override
    public void processChunk(File chunkFile) throws IOException {
        System.out.println("Processing chunk file: " + chunkFile.getName());

        try (BufferedReader br = new BufferedReader(new FileReader(chunkFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process the line (here just printing, but it could be validation or DB insert)
                //System.out.println("Processing line: " + line);

            }
        } catch (IOException e) {
            System.err.println("Error processing chunk: " + chunkFile.getName());
            throw e;
        }
    }

    // This is the run method for handling the task in an executor thread
    @Override
    public void run() {
        try {
            // Create a File object for the chunk file and process it
            File chunkFile = new File(chunkFilePath);
            processChunk(chunkFile);
        } catch (IOException e) {
            System.err.println("Error while processing chunk: " + e.getMessage());
        }
    }
}




