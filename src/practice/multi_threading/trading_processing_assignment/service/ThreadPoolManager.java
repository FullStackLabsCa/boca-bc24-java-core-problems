package practice.multi_threading.trading_processing_assignment.service;

import practice.multi_threading.trading_processing_assignment.connection_pool.HikariCPDataSource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {

    private final ExecutorService executorService;
    private final HikariCPDataSource dataSource;

    public ThreadPoolManager(int numberOfThreads, HikariCPDataSource dataSource) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
        this.dataSource = dataSource;
    }

    // Method to assign chunks to threads
    public void processChunks(List <String> listOfChunkFileNames , int numberOfChunks)
            throws IOException {
        for (int i = 0; i < numberOfChunks; i++) {
            // Submit the chunk to the thread pool
            ChunkProcessor chunkProcessor = new ChunkProcessor(listOfChunkFileNames.get(i), dataSource);
            executorService.submit(chunkProcessor);
        }
    }

    // Shutdown thread pool after completion
    public void shutdown() {
        executorService.shutdown();
    }
}
