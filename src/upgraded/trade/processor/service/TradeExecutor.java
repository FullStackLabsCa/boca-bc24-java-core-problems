package upgraded.trade.processor.service;


import com.zaxxer.hikari.HikariDataSource;
import upgraded.trade.processor.repository.TradeQueueManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TradeExecutor {
    ExecutorService tradeProcessorService= Executors.newFixedThreadPool(3);
    HikariDataSource hikariDataSource;

    public void submitTrade(HikariDataSource hikariDataSource){
        this.hikariDataSource=hikariDataSource;
        tradeProcessorService.submit(new TradeProcessor(TradeQueueManager.getQueueOne(), hikariDataSource));
        tradeProcessorService.submit(new TradeProcessor(TradeQueueManager.getQueueOne(), hikariDataSource));
        tradeProcessorService.submit(new TradeProcessor(TradeQueueManager.getQueueOne(), hikariDataSource));
        tradeProcessorService.shutdown();
    }








}
