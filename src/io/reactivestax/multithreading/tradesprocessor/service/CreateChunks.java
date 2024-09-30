package io.reactivestax.multithreading.tradesprocessor.service;

import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.multithreading.tradesprocessor.config.DatabaseHelper;
import io.reactivestax.multithreading.tradesprocessor.interfaces.ChunkGenerator;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateChunks {

    private int chunkFileSize;
    int totalLines;
    HikariDataSource hikariDataSource;

    public int getChunkFileSize() {
        return chunkFileSize;
    }

    public void setChunkFileSize(int chunkFileSize) {
        this.chunkFileSize = chunkFileSize;
    }

    public void generateChunks(String filePath, int numberOfChunks, HikariDataSource dataSource) {
        hikariDataSource = dataSource;
        try {
            totalLines = countLinesInFile(filePath);
            setChunkFileSize(totalLines / numberOfChunks);
            createChunkFile(filePath, getChunkFileSize(), numberOfChunks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void createChunkFile(String filePath, int chunkFileSize, int numberOfChunks) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfChunks);
        String chunkDestination = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/io/reactivestax/multithreading/tradesprocessor/resources/";
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
                    executorService.submit(new ProcessChunk(filecount, hikariDataSource));
                    chunk++;
                    filecount++;
                    if (numberOfChunks == filecount) {
                        writer.close();
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
            System.out.println(e);
        }
    }

    public int countLinesInFile(String filePath) throws IOException {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filePath))) {
            return (int) lineReader.lines().count() - 1;
        }
    }
}
