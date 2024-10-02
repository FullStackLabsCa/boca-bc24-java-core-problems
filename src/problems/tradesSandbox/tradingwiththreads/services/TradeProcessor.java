//package problems.tradesSandbox.tradingwiththreads.services;
//
//import com.zaxxer.hikari.HikariDataSource;
//import problems.tradingwiththreads.services.TradeProcessorRunnable;
//
//import java.io.BufferedReader;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingDeque;
//
//public class TradeProcessor {
//
//    static ExecutorService threadsForTradeProcessor = Executors.newFixedThreadPool(1);
//
//    public static  void submitTaskToThreads(LinkedBlockingDeque<String> queueOne, LinkedBlockingDeque<String> queueTwo, LinkedBlockingDeque<String> queueThree, HikariDataSource dataSource){
//
//        threadsForTradeProcessor.submit(new TradeProcessorRunnable(queueOne, dataSource));
////        threadsForTradeProcessor.submit(new TradeProcessorRunnable(queueTwo, "Two"));
////        threadsForTradeProcessor.submit(new TradeProcessorRunnable(queueThree, "Three"));
//    }
//}
