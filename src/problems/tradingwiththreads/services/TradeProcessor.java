package problems.tradingwiththreads.services;

import java.io.BufferedReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeProcessor {

    static ExecutorService threadsForTradeProcessor = Executors.newFixedThreadPool(3);

    public static  void submitTaskToThreads(){
        threadsForTradeProcessor.submit(new TradeProcessorRunnable(QueueDistributor.queueOne));
        threadsForTradeProcessor.submit(new TradeProcessorRunnable(QueueDistributor.queueTwo));
        threadsForTradeProcessor.submit(new TradeProcessorRunnable(QueueDistributor.queueThree));
    }
}
