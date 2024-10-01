package tradeprocessor.tradereader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChunkGenerator {

    public static List<String> splitFile(File file, int numberOfChunks) throws IOException {
        List<String> chunkFiles = new ArrayList<>();
        int totalLine = 0;
        File directory = new File("src/tradeprocessor/files/chunks/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.readLine() != null) {
                totalLine++;
            }
        }
        int chunkSize = totalLine / numberOfChunks;
        int remaningLines = totalLine % numberOfChunks;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.readLine();
                String line;
                int currentFileReadCount = 0;
                for(int fileNumber = 1; fileNumber <= numberOfChunks;fileNumber++){
                    String chunkFileName = "src/tradeprocessor/files/chunks/chunk" + fileNumber + ".csv";
                    File chunkFile = new File(chunkFileName);
                    chunkFiles.add(chunkFileName);
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(chunkFile))) {
                        int currentLineCount = 0;
                        int currentChunkSize = chunkSize + (fileNumber <= remaningLines ? 1 : 0);
                        while (currentLineCount < currentChunkSize && (line = br.readLine()) != null) {
                            bw.write(line);
                            bw.newLine();
                            currentLineCount++;
                            currentFileReadCount++;
                        }
                    }
                }
            }
        return chunkFiles;
    }
}