package multithreadingtrade.services;

import multithreadingtrade.tradeinterfaces.ChunkGenerator;

import java.io.*;
import java.util.Properties;


/**
 * Reads the chunk file and insert into the raw table
 */
@SuppressWarnings("java-S106")
public class CreateChunks implements ChunkGenerator {

    static int chunkFileSize;
    int totalLines;

    @Override
    public void generateChunks(String filePath, int numberOfChunks) {
        try {
            totalLines = countLinesInFile(filePath);
            chunkFileSize = totalLines / numberOfChunks;
            createChunkFiles(filePath, numberOfChunks);
        } catch (IOException e) {
            throw new RuntimeException("Error generating chunks: " + e.getMessage(), e);
        }
    }

    private void createChunkFiles(String filePath, int numberOfChunks) {
        String outputDirectory = "/Users/Gagandeep.Kaur/source/students/boca-bc24-java-core-problems/src/multithreadingtrade/utility/chunks/"; //  destination path
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            reader.readLine();
            String line;
            for (int chunk = 0; chunk < numberOfChunks; chunk++) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + "trades" + chunk + ".csv"))) {
                    for (int lineCount = 0; lineCount < chunkFileSize && (line = reader.readLine()) != null; lineCount++) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
            System.out.println("Chunks created successfully!");
        } catch (IOException e) {
            throw new RuntimeException("Error creating chunk files: " + e.getMessage(), e);
        }
    }

    public int countLinesInFile(String filePath) throws IOException {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filePath))) {
            return (int) lineReader.lines().count(); // Count all lines
        }
    }

    public static int getNumberOfChunks(String propertiesFilePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(fileInputStream);
            return Integer.parseInt(properties.getProperty("numberOfChunks"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties file not found: " + e.getMessage());
        }
    }
}
