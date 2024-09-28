package problems.tradeProcessing.trades;

import problems.tradeProcessing.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessingPool {

    private ExecutorService executorService;
    private LinkedBlockingDeque<String>[] tradeQueues;
    private Connection connection;

    public TradeProcessingPool(ExecutorService executorService, LinkedBlockingDeque<String>[] tradeQueues, Connection connection) {
        this.executorService = Executors.newFixedThreadPool(3); // 3 threads for 3 queue
        this.tradeQueues = tradeQueues;
        this.connection = connection;
    }

    public void startProcessing(){
        try{
            for (int i = 0; i < 3; i++) {
                executorService.submit(new TradeProcessor(tradeQueues, connection));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopProcessing() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
