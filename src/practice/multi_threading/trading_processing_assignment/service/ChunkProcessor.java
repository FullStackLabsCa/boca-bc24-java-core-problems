package practice.multi_threading.trading_processing_assignment.service;

import practice.multi_threading.trading_processing_assignment.connection_pool.HikariCPDataSource;
import practice.multi_threading.trading_processing_assignment.contracts.IChunkProcessor;

import java.io.IOException;
import java.util.List;

public class ChunkProcessor implements IChunkProcessor {

    @Override
    public void chunkProcessor(int numberOfChunks, List<String> listOfChunkFileName) {
        HikariCPDataSource dataSource = new HikariCPDataSource(); // initialize the HikariCP
        QueueDistributor queueDistributor = new QueueDistributor(); // Initialize QueueDistributor

        // Create ThreadPoolManager and process the CSV chunk file and send it to raw table database trade_payload
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(numberOfChunks, dataSource, queueDistributor);
        try {
            // Pass the List of the Chunk file names and number of threads required
            threadPoolManager.processChunks(listOfChunkFileName);
        } catch (IOException e) {
            System.out.println("writeRawFileToDatabase.getMessage() = " + e.getMessage());
        }
        threadPoolManager.shutdown();
    }
}
