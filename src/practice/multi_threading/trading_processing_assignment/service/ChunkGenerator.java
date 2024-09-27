package practice.multi_threading.trading_processing_assignment.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChunkGenerator {

    public static List<String> fileChunkGenerator(String inputFilePath, int numberOfChunks) {
        List<String> chunkFileNames = new ArrayList<>();
        String outputBaseFileName = "/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/practice/multi_threading/trading_processing_assignment/files/output_files/trade";

        try {
            // Step 1: Read the CSV file
            List<String> lines = readCSVFile(inputFilePath);

            // Step 2: Split into chunks
            String headerLine = lines.get(0); // Header
            List<String> listOfDataLines = lines.subList(1, lines.size()); // All lines after the header are data

            int totalLines = listOfDataLines.size();
            int chunkSize = totalLines / numberOfChunks;
            int remainder = totalLines % numberOfChunks;

            List<List<String>> chunks = new ArrayList<>();
            int start = 0;
            for (int i = 0; i < numberOfChunks; i++) {
                int end = start + chunkSize + (i < remainder ? 1 : 0); // Distribute remainder lines
                chunks.add(listOfDataLines.subList(start, Math.min(end, totalLines)));
                start = end;
            }

            // Step 3: Write each chunks of lines to a separate CSV file
            chunkFileNames = writeChunksToFiles(chunks, outputBaseFileName);

            System.out.println("CSV file split into " + numberOfChunks + " chunks successfully.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while processing the file: " + e.getMessage());
        }

        return chunkFileNames;
    }

    private static List<String> readCSVFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    private static List<String> writeChunksToFiles(List<List<String>> chunks, String baseFileName) throws IOException {
        List<String> chunkFileNames = new ArrayList<>();
        for (int i = 0; i < chunks.size(); i++) {
            String outputFileName = String.format("%s_part_%02d.csv", baseFileName, i + 1);
            List<String> outputLines = new ArrayList<>();
            outputLines.addAll(chunks.get(i)); // Add the chunk data
            Files.write(Paths.get(outputFileName), outputLines);
            chunkFileNames.add(outputFileName);
        }
        return chunkFileNames; // Return the list of chunk file names
    }
}
