package thread.tradeprocess.service;

import com.zaxxer.hikari.HikariDataSource;

import thread.tradeprocess.databaseconnection.MysqlDBConnection;
import thread.tradeprocess.tradecontract.DatabaseConnection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeService {

    private TradeService() {}
    public static HikariDataSource dataSource;
    protected static LinkedBlockingDeque<String> tradeDeQueue = new LinkedBlockingDeque<>();
    public static void setupDbConnectionAndReadFile(String filePath, int numberOfChunks) {
        DatabaseConnection mysqlDbConnection = new MysqlDBConnection();
        dataSource = mysqlDbConnection.configureHikariCP("jdbc:mysql://localhost:3306/trades");

        GenerateChunks.readCSVFile(dataSource, filePath, numberOfChunks);

        System.out.println("Trade processing started......");
        processTradesWithMultiThreading(dataSource);

        TradeRetry.retryTrades(dataSource, tradeDeQueue);
    }

    public static void processTradesWithMultiThreading(HikariDataSource dataSource) {
        int numberOfThreads = 3;
        try (ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)) {

            executorService.submit(new TradeProcessor(dataSource, TradeDistributors.tradesQueue1));
            executorService.submit(new TradeProcessor(dataSource, TradeDistributors.tradesQueue2));
            executorService.submit(new TradeProcessor(dataSource, TradeDistributors.tradesQueue3));

            executorService.shutdown();
        }
    }
}
