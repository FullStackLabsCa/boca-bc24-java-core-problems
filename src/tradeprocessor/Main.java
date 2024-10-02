package tradeprocessor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tradeprocessor.exceptions.ChunkProcessingException;
import tradeprocessor.tradeprocessor.TradeProcessor;
import tradeprocessor.tradereader.ChunkGenerator;
import tradeprocessor.tradereader.ChunkProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
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

            } catch (ChunkProcessingException | IOException e) {
                logger.error("Error processing chunks", e);
            }
        });

        singleThreadExecutor.shutdown();

    }

    private static void startChunkProcessing() {

        System.out.println("Start processing Chunk");
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (String filePath : chunkFiles) {
            Future<?> future = executorService.submit(() -> {
                try {
                    ChunkProcessor chunkProcessor = null;
                    chunkProcessor = new ChunkProcessor(filePath);
                    chunkProcessor.run();
                } catch (Exception e) {
                    logger.error("Error while processing chunk: {}", filePath, e);
                }
            });

            futures.add(future);
        }
        for(Future<?> future : futures){
            try{
                future.get();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                logger.error("Thread was interrupted while waiting for future to complete",e);
            } catch (ExecutionException e) {
                logger.error("Error occurred while executing future",e.getCause());
            }
        }
        executorService.shutdown();
        System.out.println("Executor service shut down - " + executorService.isShutdown());

    }

    public static void startTradeProcessing(){
        System.out.println("Start processing Trade : ");

        ExecutorService tradeProcessorThreadPool = Executors.newFixedThreadPool(3);
        tradeProcessorThreadPool.submit(() -> new TradeProcessor(ChunkProcessor.getQueue1()));
        tradeProcessorThreadPool.submit(() -> new TradeProcessor(ChunkProcessor.getQueue2()));
        tradeProcessorThreadPool.submit(() -> new TradeProcessor(ChunkProcessor.getQueue3()));

        tradeProcessorThreadPool.shutdown();
    }


}
