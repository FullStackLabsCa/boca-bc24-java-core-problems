package problems.tradeProcessing.chunk;

import problems.tradeProcessing.interfaceFiles.ChunkGenerator;
import problems.tradeProcessing.interfaceFiles.ChunkProcessor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChunkGenerator implements ChunkGenerator {

    private int chunkSize;
    private String outputDirectory, propertiesFilePath, rawCSV_filePath;
    private Connection connection;

    public SimpleChunkGenerator(String outputDirectory, String propertiesFilePath, String rawCSV_filePath, Connection connection) {
        this.outputDirectory = outputDirectory;
        this.propertiesFilePath = propertiesFilePath;
        this.rawCSV_filePath = rawCSV_filePath;
        this.connection=connection;
        // Load properties
        loadprooperties(propertiesFilePath);
        // called generateChunks interface
        generateChunks(rawCSV_filePath);
    }

    private void loadprooperties(String propertiesFilePath) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(inputStream);
            chunkSize = Integer.parseInt(properties.getProperty("numberOfChuncks"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<String> generateChunks(String rawCSV_filePath) {
        List<String> chunkFilePaths = new ArrayList<>();
        // Read CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(rawCSV_filePath))) {
            String line;
            int lineCount = 0;
            int chunkNumber = 1;

            // Read all lines from the CSV
            // stor all lines in a list
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Create output Directory if it dowsn't exist
            Path dirPath = Paths.get(outputDirectory);
            if (!Files.exists((dirPath))) {
                Files.createDirectories(dirPath);
            }


            // Craeate a BufferedWriter to write to new chunk files
            BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath.resolve("trade_" + chunkNumber + ".csv").toFile()));
            // After creating each chunk file, add its path to the list
            String chunkFilePath = dirPath.resolve("trade_" + chunkNumber + ".csv").toString();
            chunkFilePaths.add(chunkFilePath);
            String header = lines.get(0); // store header line
            writer.write(header); // write header
            writer.newLine();

            // write lines into chunks
            for (int i = 1; i < lines.size(); i++) {
                writer.write(lines.get(i));
                writer.newLine();
                lineCount++;

                // check if chunk size is reached
                if (lineCount % chunkSize == 0 && i + 1 < lines.size()) {
                    writer.close(); // close current writer
                    chunkNumber++;
                    writer = new BufferedWriter(new FileWriter(dirPath.resolve("trade_" + chunkNumber + ".csv").toFile()));
                    writer.write(header); // write header again for new chunk
                    writer.newLine();
                }
            }
            System.out.println("Chunk Size: "+ chunkSize);
            writer.close();
            System.out.println("Total files created: " + chunkNumber);

            ExecutorService executorService = Executors.newFixedThreadPool(chunkSize);

            for (int i = 1; i <= chunkNumber; i++) {
                chunkFilePath = dirPath.resolve("trade_" + i + ".csv").toString();
                int finalChunkNumber = chunkNumber;
                String finalChunkFilePath = chunkFilePath;
                executorService.submit(() -> {
                    ChunkProcessor chunkProcessor = new SimpleChunkProcessor(finalChunkNumber, connection);
                    chunkProcessor.processChunk(Collections.singletonList(finalChunkFilePath));
                });
            }

            executorService.shutdown();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("CSV file not found: " + rawCSV_filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading/writing files", e);
        }

        return chunkFilePaths;
    }
}
