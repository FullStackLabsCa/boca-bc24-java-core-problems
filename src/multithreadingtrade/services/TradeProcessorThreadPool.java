package multithreadingtrade.services;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TradeProcessorThreadPool {
//
//    private final ExecutorService executorService;
//
//
//    public TradeProcessorThreadPool(int tradeProcessorThreadPoolSize) {
//        this.executorService = Executors.newFixedThreadPool(tradeProcessorThreadPoolSize);
//    }

    public static void putTradesIntoQueue(int tradeProcessorThreadPoolSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(tradeProcessorThreadPoolSize);

        Future<?> future1 = executorService.submit(new TradeProcessorRunnable(QueueDistributor.tradeQueueOne));
        Future<?> future2 = executorService.submit(new TradeProcessorRunnable(QueueDistributor.tradeQueueSecond));
        Future<?> future3 = executorService.submit(new TradeProcessorRunnable(QueueDistributor.tradeQueueThird));

        while ( !( future1.isDone() && future2.isDone() || future3.isDone()) ) {
         try {
             Thread.sleep(50);
         } catch (InterruptedException ie) {
             ie.printStackTrace();
         }
        }

    }

//    public void shutDown() {
//        executorService.shutdown();
//    }
}
