package io.reactivestax.multithreading.tradesprocessor;


import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.multithreading.tradesprocessor.config.DatabaseHelper;
import io.reactivestax.multithreading.tradesprocessor.service.CreateChunks;
import io.reactivestax.multithreading.tradesprocessor.service.ProcessQueues;
import io.reactivestax.multithreading.tradesprocessor.utils.GetDataFromProperties;

public class Main {
    static String filePath = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/io/reactivestax/multithreading/tradesprocessor/resources/trades.csv";
    public static void main(String[] args) {
        HikariDataSource hikariDataSource = DatabaseHelper.getConnection("3306");
        ProcessQueues processQ = new ProcessQueues();
        GetDataFromProperties getDataFromProperties = new GetDataFromProperties();
        int chunkSize = Integer.parseInt(getDataFromProperties.readValueFromPropertiesFile("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/io/reactivestax/multithreading/tradesprocessor/resources/ data.properties", "chunks.size"));
        CreateChunks createChunks = new CreateChunks();
        createChunks.generateChunks(filePath, chunkSize, hikariDataSource); // 10 thread
        System.out.println("Processing Queues");
        processQ.processQueue(hikariDataSource); // 3 thread
    }
}
