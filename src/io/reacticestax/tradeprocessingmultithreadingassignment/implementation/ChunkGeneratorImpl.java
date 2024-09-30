package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.ChunkGenerator;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChunkGeneratorImpl implements ChunkGenerator {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int lineCount = 0;

    // Define the directory for storing chunk files
    public static final String CHUNK_DIR = "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/files/chunks/";

    @Override
    public int countLines(File file) throws FileNotFoundException {
        lineCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            boolean isFirstLine = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip header line
                } else {
                    lineCount++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineCount;
    }

    @Override
    public void generateChunkAndSubmit(File file) throws IOException {
        int totalLineCount = countLines(file);
        if (totalLineCount == 0) {
            System.out.println("No data to process in the file.");
            return;
        }

        int numberOfChunks = 10;
        int linesPerChunk = totalLineCount / numberOfChunks;
        int remainingLines = totalLineCount % numberOfChunks;

        // Add remaining lines to last chunk
        int chunkCount = 1;
        int currentLine = 0;

        BufferedWriter writer = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            int linesInCurrentChunk = 0;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // Skip the header line
                }

                if (writer == null) {
                    // Create a new chunk file with proper naming format
                    String chunkFileName = String.format("file_chunk_%02d.csv", chunkCount);
                    File chunkFile = new File(CHUNK_DIR + chunkFileName);
                    writer = new BufferedWriter(new FileWriter(chunkFile));
                   // System.out.println("Creating chunk: " + chunkFile.getAbsolutePath());
                }

                writer.write(line);
                writer.newLine();
                currentLine++;
                linesInCurrentChunk++;

                // Handle chunk size and switch to a new chunk after reaching `linesPerChunk`
                if (linesInCurrentChunk == linesPerChunk || (chunkCount == numberOfChunks && remainingLines > 0)) {
                    // For the last chunk, handle remaining lines
                    if (chunkCount == numberOfChunks) {
                        remainingLines--;  // Add one line to the last chunk if we have remaining lines
                    }

                    writer.close();
                    executorService.submit(new ChunkProcessorImpl(CHUNK_DIR + String.format("file_chunk_%02d.csv", chunkCount)));
                    chunkCount++;
                    linesInCurrentChunk = 0;
                    writer = null;

                    // Stop creating more chunks if we've reached the number of chunks
                    if (chunkCount > numberOfChunks) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}

