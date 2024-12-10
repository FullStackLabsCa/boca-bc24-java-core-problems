package chunkproblem.service;

import chunkproblem.interfaces.ChunkFileProcessor;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChunkFileGenerator implements ChunkFileProcessor {
    private int chunksCount; // This should represent the number of lines per chunk
    HikariDataSource dataSource = new HikariDataSource();

    public void readCSVFile(String filepath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filepath)) {
            properties.load(fileInputStream);
            chunksCount = Integer.parseInt(properties.getProperty("chunk.size"));
            System.out.println("Number of lines per chunk: " + chunksCount);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> chunkIntoTrades(String filepath) {
        List<String> listOfChunks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int rowCount = 0;
            int currentChunkNumber = 1;

            // Skip the header line
            if ((line = reader.readLine()) != null) {
                // Header line skipped
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + "_chunk_" + currentChunkNumber + ".csv"));

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                rowCount++;

                if (rowCount >= chunksCount) {
                    writer.close(); // Close current chunk file
                    listOfChunks.add(filepath + "_chunk_" + currentChunkNumber + ".csv"); // Add to list of chunk files
                    currentChunkNumber++;
                    rowCount = 0;

                    // Open a new writer for the next chunk
                    writer = new BufferedWriter(new FileWriter(filepath + "_chunk_" + currentChunkNumber + ".csv"));
                }
            }

            if (rowCount > 0) { // Handle any remaining lines
                writer.close();
                listOfChunks.add(filepath + "_chunk_" + currentChunkNumber + ".csv");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing chunks: " + e.getMessage());
        }

        return listOfChunks;
    }

    public void creatingChunkFile(String outputDirPath, List<String> chunkPaths) {
        File outputDir = new File(outputDirPath);
        for (String chunkPath : chunkPaths) {
            try (BufferedReader reader = new BufferedReader(new FileReader(chunkPath))) {
                String line;
                List<String> lines = new ArrayList<>();
                if ((line = reader.readLine()) != null) {

                }
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputDir, new File(chunkPath).getName())));
                for (String chunkLine : lines) {
                    writer.write(chunkLine);
                    writer.newLine();
                }
                writer.close();
                System.out.println("Processed chunk: " + chunkPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
