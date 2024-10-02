package upgraded.trade.processor.service;

import com.zaxxer.hikari.HikariDataSource;
import upgraded.trade.processor.database.DataSourceTrading;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeService {
    private int numberOfChunks;
    ExecutorService chunkProcessorThreadPool = Executors.newFixedThreadPool(10);
    HikariDataSource dataSource= DataSourceTrading.createDataSource();
    public void startTradeProcessor(){
        TradeExecutor tradeExecutor = new TradeExecutor();
        tradeExecutor.submitTrade(dataSource);
    }
    // Configure the number of chunks based on properties file
    public void configureChunks(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(STR."/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/upgraded/trade/processor/utility/\{filePath}")) {
            properties.load(input);
            numberOfChunks = Integer.parseInt(properties.getProperty("numberOfChunks", "1"));
            System.out.println(numberOfChunks);
        }
    }

    // Chunk the trades file into specified number of chunks
    public List<String> chunkTrade(String filePath) {
        List<String> chunks = new ArrayList<>();
        StringBuilder currentChunk = new StringBuilder();
        long lineCount = 0;

        // First, count total lines
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return chunks; // Return empty list if error occurs
        }

        // Calculate the lines per chunk
        long linesPerChunk = (long) Math.ceil((double) lineCount / numberOfChunks);

        // Chunk the trades file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLines = 0;

            while ((line = br.readLine()) != null) {
                currentChunk.append(line).append(System.lineSeparator());
                currentLines++;

                if (currentLines >= linesPerChunk) {
                    chunks.add(currentChunk.toString());
                    currentChunk.setLength(0);  // Reset for the next chunk
                    currentLines = 0; // Reset line counter for the next chunk
                }
            }

            // Add the last chunk if it's not empty
            if (currentChunk.length() > 0) {
                chunks.add(currentChunk.toString());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return chunks;
    }

    // Write each chunk to a separate CSV file
    public void writeChunksToFiles(List<String> chunks, String outputDir) {

        // Ensure the output directory exists
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it does not exist
        }
        for (int i = 0; i < chunks.size(); i++) {
            String chunk = chunks.get(i);
            String filePath = String.format("%s/chunk_%d.csv", outputDir, i + 1); // Change .txt to .csv
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(chunk);
                System.out.println("Written chunk to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
            }

            chunkProcessorThreadPool.submit(new ChunkProcessor(filePath,dataSource));
        }
    }
}
