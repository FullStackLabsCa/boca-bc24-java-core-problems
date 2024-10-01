package tradeprocessor;


import tradeprocessor.tradeprocessor.TradeProcessor;
import tradeprocessor.tradereader.ChunkGenerator;
import tradeprocessor.tradereader.ChunkProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    static List<String> chunkFiles;
    static List<Future<?>> futures = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        File file = new File("/Users/Mann.Bhatt/source/problems/mabh/boca-bc24-java-core-problems/src/tradeprocessor/files/trades.csv");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            try {
                chunkFiles = ChunkGenerator.splitFile(file, 10);
                System.out.println(chunkFiles);

                startChunkProcessing();
                startTradeProcessing();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        singleThreadExecutor.shutdown();

    }

    private static void startChunkProcessing() throws Exception {

        System.out.println("Start processing Chunk");
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (String filePath : chunkFiles) {
            Future<?> future = executorService.submit(() -> {
                try {
                    ChunkProcessor chunkProcessor = new ChunkProcessor(filePath);
                    chunkProcessor.run();


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
            }
        }
        executorService.shutdown();
        System.out.println("Executor service shut down - " + executorService.isShutdown());

    }

    public static void startTradeProcessing() throws Exception {
        System.out.println("Start processing Trade : ");

        ExecutorService tradeProcessorThreadPool = Executors.newFixedThreadPool(3);
        tradeProcessorThreadPool.submit(() -> new TradeProcessor(ChunkProcessor.getQueue1()));
        tradeProcessorThreadPool.submit(() -> new TradeProcessor(ChunkProcessor.getQueue2()));
        tradeProcessorThreadPool.submit(() -> new TradeProcessor(ChunkProcessor.getQueue3()));

        tradeProcessorThreadPool.shutdown();
    }


}
