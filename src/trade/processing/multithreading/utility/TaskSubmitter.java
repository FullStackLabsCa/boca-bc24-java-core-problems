package trade.processing.multithreading.utility;

import trade.processing.multithreading.dao.TradeProcessor;
import trade.processing.multithreading.service.TradeChunkProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskSubmitter {

    public static void submitToTradeProcessor(){
        int numberOfThreads=3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        executorService.submit(new TradeProcessor(TradeChunkProcessor.firstQue));
        executorService.submit(new TradeProcessor(TradeChunkProcessor.secondQue));
        executorService.submit(new TradeProcessor(TradeChunkProcessor.thirdQue));
        executorService.shutdown();
    }

    public static void submitRetryQue(){
        ScheduledExecutorService scheduledExecutorService =Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new TradeProcessor(TradeProcessor.deadQue),30000,30000, TimeUnit.MILLISECONDS);
        scheduledExecutorService.shutdown();
    }
}
