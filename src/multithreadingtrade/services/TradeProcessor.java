package multithreadingtrade.services;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeProcessor {

    static ExecutorService tradeProcessorThreads = Executors.newFixedThreadPool(3);

    public static void putTradesIntoQueue() {
        tradeProcessorThreads.submit(new TradeProcessorRunnable(QueueDistributor.tradeQueueOne));
        tradeProcessorThreads.submit(new TradeProcessorRunnable(QueueDistributor.tradeQueueSecond));
        tradeProcessorThreads.submit(new TradeProcessorRunnable(QueueDistributor.tradeQueueThird));
    }
}
