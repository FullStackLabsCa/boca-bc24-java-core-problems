package problems.thread.trade;


import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("java:S106")
public class ThreadTradeRunner {
    public static void main(String[] args) throws Exception {
        // read file and load data into the list
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please enter a file name >>>");
//        String fileName = scanner.next().trim();
//        ThreadTradeFileReader tradeFileReader = new ThreadTradeFileReader();
//        tradeFileReader.fileReader(fileName);

        //generating chunk files
//        new ChunkFileGenerator();

        // creating chunkProcessor ThreadPool
        int numOfThreadForChunkPool = 10;
        ExecutorService executorServiceChunkProcessor = Executors.newFixedThreadPool(numOfThreadForChunkPool);
        for (int i = 0; i < numOfThreadForChunkPool; i++) {
            String fileName = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/src/problems/thread/trade/rawdata/trade_chunk_" + (i + 1) + ".csv";
            executorServiceChunkProcessor.submit(new ChunkFileProcessor(fileName));
        }
        executorServiceChunkProcessor.shutdown();

        Thread.sleep(1000);

        System.out.println(">>>>>>>>>>Data inserted in the trade payloads table<<<<<<<<<<");

        //working on trade processor
        int numberOfThreadsForTradeProcessor = 3;
        ExecutorService executorServiceForTradeProcessor = Executors.newFixedThreadPool(numberOfThreadsForTradeProcessor);
        executorServiceForTradeProcessor.submit(new ThreadTradeProcessor(ThreadTradeService.queue1));
        Thread.sleep(1000);
        executorServiceForTradeProcessor.submit(new ThreadTradeProcessor(ThreadTradeService.queue2));
        Thread.sleep(1000);
        executorServiceForTradeProcessor.submit(new ThreadTradeProcessor(ThreadTradeService.queue3));
        Thread.sleep(1000);

        System.out.println(">>>>>>>>>>Data inserted in the journal entry table<<<<<<<<<<");
    }
}
