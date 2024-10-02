package tradeprocessor.tradereader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChunkGenerator {

    private ChunkGenerator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static List<String> splitFile(File file, int numberOfChunks) throws IOException {
        List<String> chunkFiles = new ArrayList<>();
        int totalLine = 0;
        String reader;

        if (numberOfChunks <= 0) {
            throw new IllegalArgumentException("Number of chunks must be greater than 0");
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((reader = br.readLine()) != null) {
                totalLine++;
            }
        }
        int chunkSize = totalLine / numberOfChunks;
        int remainingLines = totalLine % numberOfChunks;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                reader = br.readLine();
                String line;

                for(int fileNumber = 1; fileNumber <= numberOfChunks;fileNumber++){
                    String chunkFileName = "src/tradeprocessor/files/chunks/chunk" + fileNumber + ".csv";
                    File chunkFile = new File(chunkFileName);
                    chunkFiles.add(chunkFileName);
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(chunkFile))) {
                        int currentLineCount = 0;
                        int currentChunkSize = chunkSize + (fileNumber <= remainingLines ? 1 : 0);
                        while (currentLineCount < currentChunkSize && (line = reader) != null) {
                            bw.write(line);
                            bw.newLine();
                            currentLineCount++;

                        }
                    }
                }
            }
        return chunkFiles;
    }
}