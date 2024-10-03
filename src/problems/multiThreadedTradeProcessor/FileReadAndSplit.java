package problems.multiThreadedTradeProcessor;

import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class FileReadAndSplit {
    static int chunkSize;
    static String mainTradesFile;
    static String chunkedFilePath;
    static HikariDataSource dataSource;

    public static List<String> chunkPaths = new ArrayList<>();

    public static int getChunkSize() {
        Properties properties = new Properties();
        try {
            properties.load(new java.io.FileReader("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/multiThreadedTradeProcessor/ThreadsUtilities/application.properties"));
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

        mainTradesFile = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/multiThreadedTradeProcessor/ThreadsUtilities/trades (1).csv";
        chunkedFilePath = "/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/multiThreadedTradeProcessor/chunkedfilespath";
        int numberOfRowsInFile = 0;
        List<String> allTradeRecords = new ArrayList<>();
        String tradeRecord;
        dataSource = dataSourceInitialized;
        int currentChunk = 1;
        int rowNumber = 0;

        //reading file
        try (BufferedReader reader = new BufferedReader(new FileReader(mainTradesFile))) {
            reader.readLine();
            while ((tradeRecord = reader.readLine()) != null) {
                numberOfRowsInFile++;
                allTradeRecords.add(tradeRecord);
            }
        }

        writeRecordsToChunks(currentChunk, numberOfRowsInFile, allTradeRecords, rowNumber);

    }

    private static void writeRecordsToChunks(int currentChunk, double numberOfRowsInFile, List<String> allTradeRecords, int rowNumber) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(chunkedFilePath + "/Trade_" + currentChunk + ".csv"));
        double linesPerChunk = numberOfRowsInFile / getChunkSize();
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
                chunkPaths.add(chunkedFilePath+"/"+chunkedFileName);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
        generateChunksAndSubmitTask(dataSource);
        for(String filePath : chunkPaths){
            System.out.println(filePath);
        }
    }
}