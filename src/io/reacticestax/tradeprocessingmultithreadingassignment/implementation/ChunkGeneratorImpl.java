package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.ConfigLoader;
import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.ChunkGenerator;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChunkGeneratorImpl implements ChunkGenerator {
    ConfigLoader configLoader = new ConfigLoader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/Application.properties");

    public Integer numberOfChunks = configLoader.getIntProperty("number.of.chunks");
    public static final File file = new File("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/files/trades.csv");

    public ExecutorService executorService = Executors.newFixedThreadPool(numberOfChunks);

    private int lineCount = 0;
    public static final String CHUNK_DIR = "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/files/chunks/";

    @Override
    public int countLines(File file) throws FileNotFoundException {
         lineCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            boolean isFirstLine = true;
            while (reader.readLine() != null) {
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


        int linesPerChunk = totalLineCount / numberOfChunks;
        int remainingLines = totalLineCount % numberOfChunks;


        int chunkCount = 1;

        BufferedWriter writer = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            int linesInCurrentChunk = 0;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (writer == null) {
                    String chunkFileName = String.format("file_chunk_%02d.csv", chunkCount);
                    File chunkFile = new File(CHUNK_DIR + chunkFileName);
                    writer = new BufferedWriter(new FileWriter(chunkFile));
                   // System.out.println("Creating chunk: " + chunkFile.getAbsolutePath());
                }

                writer.write(line);
                writer.newLine();
                linesInCurrentChunk++;

                // Handle chunk size and switch to a new chunk after reaching linesPerChunk
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

