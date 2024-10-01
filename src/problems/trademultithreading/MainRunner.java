package problems.trademultithreading;

import problems.trademultithreading.customexception.FileSplitterException;
import problems.trademultithreading.services.DatabaseProcessor;
import problems.trademultithreading.services.FileSplitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MainRunner {
    private static final String PROPERTIES_FILE = "/Users/Rushikumar.Patel/source/problems/src/problems/trademultithreading/application.properties";
    private static int numberOfChunks;

    private static int loadProperties() throws FileSplitterException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
            return numberOfChunks = Integer.parseInt(properties.getProperty("numberOfChunks"));
        } catch (IOException | NumberFormatException e) {
            throw new FileSplitterException("Failed to load properties: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        String inputDir = "/Users/Rushikumar.Patel/source/problems/src/problems/trademultithreading/csvfiles/trades-2.csv";
        String outputDir = "/Users/Rushikumar.Patel/source/problems/src/problems/trademultithreading/csvfiles/chunkfiles";

        try {
            numberOfChunks = loadProperties();
            System.out.println("Loaded number of chunks: " + numberOfChunks);

            // Step 1: Split the large CSV file into chunks
            FileSplitter splitter = new FileSplitter(numberOfChunks);
            splitter.startSplitting(new File(inputDir), outputDir);

            // Step 2: Initialize the queue distributor map (account number to queue mapping)
            ConcurrentHashMap<String, String> queueDistributorMap = new ConcurrentHashMap<>();

            // Step 3: Process chunks and assign trades to queues
            DatabaseProcessor processor = new DatabaseProcessor(numberOfChunks, queueDistributorMap);
            processor.processChunks(outputDir);

        } catch (FileSplitterException e) {
            System.err.println("FileSplitterException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
