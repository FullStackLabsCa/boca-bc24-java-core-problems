package multithreading.Trades.service;

import multithreading.Trades.interfaces.ChunkGenerator;

import java.io.*;

public class CreateChunks implements ChunkGenerator {

    static int chunkFileSize;
    int totalLines;

    @Override
    public void generateChunks(String filePath, int numberOfChunks) {
        try {
            totalLines = countLinesInFile(filePath);
            chunkFileSize = totalLines / numberOfChunks;
            createChunkFile(filePath, chunkFileSize, numberOfChunks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createChunkFile(String filePath, int chunkFileSize, int numberOfChunks) {

        String chunkDestination = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/multithreading/Trades/resources/ ";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            int chunk = 0;
            String line;
            int lineCount = 0;
            int filecount = 0;
            BufferedWriter writer = new BufferedWriter(new FileWriter(chunkDestination + "chunkFile" + chunk + ".csv"));
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                lineCount++;
                if (lineCount >= chunkFileSize) {

                    writer.close();
                    chunk++;
                    filecount++;
                    if (numberOfChunks == filecount) {
                        break;
                    } else {
                        writer = new BufferedWriter(new FileWriter(chunkDestination + "chunkFile" + filecount + ".csv"));
                    }
                    lineCount = 0;
                }
            }
            writer.close();
            System.out.println("chunks created successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int countLinesInFile(String filePath) throws IOException {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filePath))) {
            return (int) lineReader.lines().count() - 1;
        }
    }
}
