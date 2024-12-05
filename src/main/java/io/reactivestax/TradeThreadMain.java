package io.reactivestax;


import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.services.QueueDistributorSandbox;
import io.reactivestax.services.TradeProcessorSandbox;
import problems.tradingwiththreads.databaseconnector.DatabaseConnector;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.reactivestax.services.ChunkGeneratorSandbox.generateChunksAndSubmitTask;



public class TradeThreadMain {

    public static HikariDataSource dataSource;

    public static void main(String[] args) throws IOException, InterruptedException {

       HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
        generateChunksAndSubmitTask(dataSource);
//        Thread.sleep(500);

        int numberOfThreadsForTradeProcessor = 3;
        ExecutorService executorServiceForTradeProcessor = Executors.newFixedThreadPool(numberOfThreadsForTradeProcessor);

        executorServiceForTradeProcessor.submit(new TradeProcessorSandbox(QueueDistributorSandbox.queueOne, dataSource));
//        Thread.sleep(500);

        executorServiceForTradeProcessor.submit(new TradeProcessorSandbox(QueueDistributorSandbox.queueTwo, dataSource));
//        Thread.sleep(500);

        executorServiceForTradeProcessor.submit(new TradeProcessorSandbox(QueueDistributorSandbox.queueThree, dataSource));
//        Thread.sleep(500);

        executorServiceForTradeProcessor.shutdown();


    }
}
