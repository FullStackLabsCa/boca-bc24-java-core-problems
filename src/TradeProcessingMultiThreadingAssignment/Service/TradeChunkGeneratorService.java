package TradeProcessingMultiThreadingAssignment.Service;

import java.io.*;

public class TradeChunkGeneratorService implements ChunkGeneratorInterface {
    int lineCount = 0;


@Override
public int fileLineCounter() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/TradeProcessingMultiThreadingAssignment/Files/trade_records_with_cusip.csv"))) {
            String newLine;
            boolean isFirstLine = true;

            while ((newLine = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return lineCount;
    }

@Override
public void chunkFileGenerator() throws IOException {
        int totalLineCount = fileLineCounter();
        if (totalLineCount == 0) {
            System.out.println("No data to process in the file.");
            return;
        }
        int numberOfChunks = 10;
        int linesPerChunk = totalLineCount / numberOfChunks;
        int remainingLines = totalLineCount % numberOfChunks;
        int chunkCount = 1;
        int currentLine = 0;

        BufferedWriter writer = null;
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/TradeProcessingMultiThreadingAssignment/Files/trade_records_with_cusip.csv"))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (writer == null) {

                    writer = new BufferedWriter(new FileWriter(String.format("src/TradeProcessingMultiThreadingAssignment/chunks/file_chunk_%02d.csv", chunkCount)));
                }
                writer.write(line);
                writer.newLine();
                currentLine++;

                // Handle chunk size and remainder
                if (currentLine % linesPerChunk == 0 && chunkCount < numberOfChunks) {
                    writer.close();
                    chunkCount++;
                    writer = null;
                }
            }
            if (remainingLines > 0 && currentLine % linesPerChunk != 0) {
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (writer != null) {
                writer.close();
            }
        }
    }
}






