package problems.tradingwiththreads.services;


import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.databaseconnector.DatabaseConnector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChunkGenerator {
    static HikariDataSource dataSource;
    static String mainFilePath;
    static int chunkSize;
    static String chunkedFilePath;


    public static int getChunkSize() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradingwiththreads/utility/application.properties"));
            chunkSize = Integer.parseInt(properties.getProperty("numberOfChunks"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Some error occured: " + e);
        }
        return chunkSize;
    }


    public static void generateChunksAndSubmitTask() throws IOException {
        mainFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradingwiththreads/utility/trades (1).csv";
        chunkedFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradingwiththreads/filechunks";
        int numberOfRowsInFile = 0;
        List<String> chunkFiles = new ArrayList<>();
        String line;
        dataSource = DatabaseConnector.configureHikariCP();
        //creating thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //reading file
        try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath))) {
            int currentChunk = 1;
            int rowNumber = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                numberOfRowsInFile++;
                chunkFiles.add(line);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(chunkedFilePath + "/Trade_" + currentChunk + ".csv"));


            double linesPerChunk = (double) numberOfRowsInFile / getChunkSize();
            for (int i = 0; i < chunkFiles.size(); i++) {
                writer.write(chunkFiles.get(i));
                writer.newLine();
                rowNumber++;

                if (rowNumber >= linesPerChunk || chunkFiles.indexOf(chunkFiles.get(i)) == chunkFiles.size()-1) {
                    writer.close();

                    String chunkedFileName = chunkedFilePath + "/Trade_" + currentChunk + ".csv";
                    System.out.println(chunkedFileName);

                    currentChunk++;
                    rowNumber = 0;

                    if(currentChunk <= getChunkSize()) {
                        writer = new BufferedWriter(new FileWriter(chunkedFilePath + "/Trade_" + currentChunk + ".csv"));

                    }
                    executorService.submit(new ChunkProcessor(chunkedFileName, dataSource));
                }
            }
            executorService.shutdown();
        }
    }
}


