package problems.trademultithreading;

import problems.trademultithreading.customexception.FileSplitterException;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileSplitter {

    private static final String CSV_EXTENSION = ".csv";
    private final ExecutorService singleThreadSplitter;
    int numberOfChunks;
    public FileSplitter(int numberOfChunks) throws FileSplitterException {
        singleThreadSplitter = Executors.newSingleThreadExecutor();
        this.numberOfChunks = numberOfChunks;
    }

    public void startSplitting(File inputFile, String outputDir) {
        singleThreadSplitter.submit(() -> {
            try {
                splitCSVFileWithSingleThread(inputFile, outputDir);
            } catch (FileSplitterException e) {
                System.out.println("Error while splitting file: " + e.getMessage());
            }
        });
        singleThreadSplitter.shutdown();
    }

    private void splitCSVFileWithSingleThread(File inputFile, String outputDir) throws FileSplitterException {
        validateInputFile(inputFile);
        Path outputDirectory = Path.of(outputDir);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String header = reader.readLine();
            if (header == null) {
                throw new FileSplitterException("The input file is empty.");
            }

            long totalLines = Files.lines(inputFile.toPath()).count() - 1;
            int rowsPerChunk = (int) Math.ceil((double) totalLines / numberOfChunks);

            processCSVChunks(reader, header, rowsPerChunk, outputDirectory);

        } catch (IOException e) {
            throw new FileSplitterException("Error processing the CSV file: " + e.getMessage(), e);
        }
    }

    private void processCSVChunks(BufferedReader reader, String header, int rowsPerChunk, Path outputDirectory) throws IOException {
        int chunkIndex = 0;
        List<String> rows = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            rows.add(line);
            if (rows.size() == rowsPerChunk) {
                writeChunkFile(header, rows, outputDirectory, chunkIndex++);
                rows.clear();
            }
        }

        if (!rows.isEmpty()) {
            writeChunkFile(header, rows, outputDirectory, chunkIndex);
        }
    }

    private void writeChunkFile(String header, List<String> rows, Path outputDirectory, int chunkIndex) throws IOException {
        File chunkFile = outputDirectory.resolve("chunk_" + chunkIndex + CSV_EXTENSION).toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFile))) {
            writer.write(header);
            writer.newLine();
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
        }
    }

    private void validateInputFile(File inputFile) throws FileSplitterException {
        if (!inputFile.exists() || inputFile.isDirectory()) {
            throw new FileSplitterException("Invalid input file provided: " + inputFile.getAbsolutePath());
        }
    }

//    private Path prepareOutputDirectory(String outputDir) throws FileSplitterException {
//        try {
//            Path outputDirectory = Paths.get(outputDir);
//            if (!Files.exists(outputDirectory)) {
//                Files.createDirectories(outputDirectory);
//            }
//            return outputDirectory;
//        } catch (IOException e) {
//            throw new FileSplitterException("Failed to create output directory: " + outputDir, e);
//        }
//    }
}
