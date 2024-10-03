package problems.tradesSandbox.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChunkGeneratorSandbox {
    static HikariDataSource dataSource;
    static String mainTradesFile;
    static int chunkSize;
    static String chunkedFilePath;

    public static int getChunkSize() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradesSandbox/tradingwiththreads/utility/application.properties"));
            chunkSize = Integer.parseInt(properties.getProperty("numberOfChunks"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Some error occured: " + e);
        }
        return chunkSize;
    }

    public static void generateChunksAndSubmitTask(HikariDataSource dataSourceInitialized) throws IOException {

        mainTradesFile = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradesSandbox/tradingwiththreads/utility/trades (1).csv";
        chunkedFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/tradesSandbox/tradingwiththreads/filechunks";
        int numberOfRowsInFile = 0;
        List<String> allTradeRecords = new ArrayList<>();
        String tradeRecord;
        dataSource = dataSourceInitialized;

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        //reading file
        try (BufferedReader reader = new BufferedReader(new FileReader(mainTradesFile))) {
            int currentChunk = 1;
            int rowNumber = 0;
            reader.readLine();
            while ((tradeRecord = reader.readLine()) != null) {
                numberOfRowsInFile++;
                allTradeRecords.add(tradeRecord);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(chunkedFilePath + "/Trade_" + currentChunk + ".csv"));
            double linesPerChunk = (double) numberOfRowsInFile / getChunkSize();
            for (int i = 0; i < allTradeRecords.size(); i++) {

                writer.write(allTradeRecords.get(i));
                writer.newLine();
                rowNumber++;

                if (rowNumber >= linesPerChunk || allTradeRecords.indexOf(allTradeRecords.get(i)) == allTradeRecords.size() - 1) {
                    writer.close();

                    String chunkedFileName = chunkedFilePath + "/Trade_" + currentChunk + ".csv";

                    currentChunk++;
                    rowNumber = 0;

                    if (currentChunk <= getChunkSize()) {
                        writer = new BufferedWriter(new FileWriter(chunkedFilePath + "/Trade_" + currentChunk + ".csv"));

                    }
                    System.out.println("submitting the file to next ChunkProcessor :: " + chunkedFileName);
                    executorService.submit(new ChunkProcessorSandbox(chunkedFileName, dataSource));
                    Thread.sleep(500);
                }
            }
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


