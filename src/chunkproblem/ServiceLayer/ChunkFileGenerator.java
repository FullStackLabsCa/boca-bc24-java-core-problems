package chunkproblem.ServiceLayer;
import chunkproblem.Interfaces.ChunkFileProcessor;

import java.io.*;
import java.util.Properties;

public class ChunkFileGenerator implements ChunkFileProcessor {

    public void readCSVFile(){
        Properties properties = new Properties();
        String propertiesFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/tradingChunks/Utility/application.properties";

        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            properties.load(reader);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        int chunkCount = Integer.parseInt(properties.getProperty("chunk.size")); // parse into string
        String csvFilePath = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/tradingChunks/Utility/trade_records10.csv"; // Replace with your actual CSV file path
        System.out.println("CSV file path: " + csvFilePath); // to confirm its working
        System.out.println("Number of chunks: " + chunkCount);

        File csvFile = new File(csvFilePath); // checking if csv file is valid in path if not sout
        if (!csvFile.exists()) {
            System.out.println("CSV file does not exist at: " + csvFilePath);
            return;
        }  String outputDirectory = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/tradingChunks/NewChunkFiles/";
        creatingChunkFile(csvFilePath, chunkCount) ;
        creatingChunkFile(csvFilePath,chunkCount);

    }

    public void creatingChunkFile(String csvFilePath, int chunkCount) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int totalLines = 0;

            boolean isFirstLine = true; // removing header
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                totalLines++;
            }
            int linesPerChunk = (int) Math.ceil((double) totalLines / chunkCount);    // Calculate the number of lines per chunk

            BufferedReader newReader = new BufferedReader(new FileReader(csvFilePath));    //  have to Prepare to read the file again
            int currentChunk = 1;
            int rowCount = 0;

            newReader.readLine(); // Skip the header line again

            // Set the output directory where the chunk files will be saved
            String outputDirectory = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/tradingChunks/NewChunkFiles/";

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + "chunk_" + currentChunk + ".csv"));

            // Read and write lines to chunks
            while ((line = newReader.readLine()) != null) {
                writer.write(line);   // Write the current row to the chunk file
                writer.newLine();
                rowCount++;

                // If we reach the lines per chunk, close the writer and prepare for the next chunk
                if (rowCount >= linesPerChunk) {
                    writer.close(); // Close the current chunk file
                    rowCount = 0; // Reset row count for the next chunk
                    currentChunk++;

                    // If we've created all the chunks, exit
                    if (currentChunk > chunkCount) {
                        break;
                    }
                    writer = new BufferedWriter(new FileWriter(outputDirectory + "chunk_" + currentChunk + ".csv"));
                }
            }
            if (rowCount > 0) {
                writer.close();
            }
            System.out.println("CSV file divided into " + (currentChunk - 1) + " chunks.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
