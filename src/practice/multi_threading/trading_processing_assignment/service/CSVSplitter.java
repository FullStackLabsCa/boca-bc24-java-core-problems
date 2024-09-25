package practice.multi_threading.trading_processing_assignment.service;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CSVSplitter {

    Scanner scanner = new Scanner(System.in);

    // Function to read all lines from the CSV file
    public static List<String> readCSVFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    // Function to split the lines into N chunks
    public static List<List<String>> splitIntoChunks(List<String> lines, int chunkSize) {
        return IntStream.range(0, (lines.size() + chunkSize - 1) / chunkSize)
            .mapToObj(i -> lines.subList(i * chunkSize, Math.min(lines.size(), (i + 1) * chunkSize)))
            .collect(Collectors.toList());
    }

    // Function to write chunks of trades to separate files
    public static void writeChunksToFiles(List<List<String>> chunks, String baseFileName) throws IOException {
        for (int i = 0; i < chunks.size(); i++) {
            String outputFileName = String.format("%s_part_%02d.csv", baseFileName, i + 1);
            Files.write(Paths.get(outputFileName), chunks.get(i));
            System.out.println("Written: " + outputFileName);
        }
    }

    public static void main(String[] args) throws IOException {
        // Input CSV file with 1000 lines
        String inputFilePath = "trades.csv";
        String outputBaseFileName = "trade_split"; // Base name for output files
        
        // Step 1: Read the CSV file
        List<String> lines = readCSVFile(inputFilePath);

        // Step 2: Split into 10 equal parts (100 lines each)
        int chunkSize = lines.size() / 10; // 100 lines per chunk
        List<List<String>> chunks = splitIntoChunks(lines, chunkSize);

        // Step 3: Write each chunk to a separate CSV file
        writeChunksToFiles(chunks, outputBaseFileName);

        System.out.println("CSV file split into 10 equal parts successfully.");
    }
}
