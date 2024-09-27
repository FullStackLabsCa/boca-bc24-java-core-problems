package practice.multi_threading.trading_processing_assignment.service;

import practice.multi_threading.trading_processing_assignment.connection_pool.HikariCPDataSource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {

    private final ExecutorService executorService;
    private final HikariCPDataSource dataSource;
    private final QueueDistributor queueDistributor;

    public ThreadPoolManager(int numberOfThreads, HikariCPDataSource dataSource, QueueDistributor queueDistributor) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
        this.dataSource = dataSource;
        this.queueDistributor = queueDistributor;
    }

    // Method to assign chunks to threads
    public void processChunks(List<String> listOfChunkFileNames) throws IOException {
        for (String chunkFileName : listOfChunkFileNames) {
            // Submit the chunk to the thread pool
            ChunkProcessorUtil chunkProcessorUtil = new ChunkProcessorUtil(chunkFileName, dataSource, queueDistributor);
            executorService.submit(chunkProcessorUtil);
        }
    }

    // Shutdown thread pool after completion
    public void shutdown() {
        executorService.shutdown();
    }
}
