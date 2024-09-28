package problems.tradeUsingThread;

import problems.tradeUsingThread.fileReader.ChunkGenerator;
import problems.tradeUsingThread.processor.ChunkProcessor;
import problems.tradeUsingThread.processor.TradeProcessor;

import java.security.KeyStore;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static problems.tradeUsingThread.processor.ChunkProcessor.*;

public class MainRunner {
    public static void main(String[] args) {
        ChunkGenerator chunkGenerator = new ChunkGenerator("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/mainFile/trades.csv");
        ExecutorService singleThreadEexecutorService = Executors.newSingleThreadExecutor();
        singleThreadEexecutorService.submit(chunkGenerator);
        singleThreadEexecutorService.shutdown();


        List<String> chunkFiles = ChunkGenerator.chunkGenerator();
        System.out.println("----------------------------------------------------------List of all chunk files----------------------------------------------------------");
        System.out.println(chunkFiles);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("--------------------------------------------------Find which thread is reading which file--------------------------------------------------");
        System.out.println();

        ExecutorService chunkProcessorEexecutorService = Executors.newFixedThreadPool(10);
        for (String chunkFile : chunkFiles) {
            chunkProcessorEexecutorService.submit(() -> {
                ChunkProcessor chunkProcessor = new ChunkProcessor("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/tradeFileChunks/" + chunkFile);
                chunkProcessor.run();
            });
        }
        chunkProcessorEexecutorService.shutdown();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Concurrent Hashmap size after adding all the unique account_id: " + tradeQueueMap.size());
        System.out.println("Queue 1 size: " + queue1.size());
        System.out.println("Queue 2 size: " + queue2.size());
        System.out.println("Queue 3 size: " + queue3.size());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");

        TradeProcessor tradeProcessor1 = new TradeProcessor(queue1);
        TradeProcessor tradeProcessor2 = new TradeProcessor(queue2);
        TradeProcessor tradeProcessor3 = new TradeProcessor(queue3);
        ExecutorService tradeProcessorEexecutorService = Executors.newFixedThreadPool(3);
        tradeProcessorEexecutorService.submit(tradeProcessor1);
        tradeProcessorEexecutorService.submit(tradeProcessor2);
        tradeProcessorEexecutorService.submit(tradeProcessor3);
        tradeProcessorEexecutorService.shutdown();
    }
}
