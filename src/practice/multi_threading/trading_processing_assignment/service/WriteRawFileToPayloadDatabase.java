package practice.multi_threading.trading_processing_assignment.service;

import practice.multi_threading.trading_processing_assignment.connection_pool.HikariCPDataSource;
import practice.multi_threading.trading_processing_assignment.contracts.WriteRawFilesToDatabase;

import java.io.IOException;
import java.util.List;

public class WriteRawFileToPayloadDatabase implements WriteRawFilesToDatabase {
    @Override
    public void writeRawFileToDatabase(int numberOfChunks, List<String> listOfChunkFileName) {
        HikariCPDataSource dataSource = new HikariCPDataSource(); // initialize the HikariCP
        //Create ThreadPoolManager and process the CSV chunk file and send it to raw table database trade_payload
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(numberOfChunks, dataSource);
        try {
            // Pass the List of the Chunk file namnes and number of thread required, passing the number to initiate the same number of the threads
            threadPoolManager.processChunks(listOfChunkFileName, numberOfChunks);
        } catch (IOException e) {
            System.out.println("writeRawFileToDatabase.getMessage() = " + e.getMessage());
        }

        threadPoolManager.shutdown();

    }
}
