package problems.tradingwiththreads;


import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.model.JournalEntryPOJO;
import problems.tradingwiththreads.repository.TradesRepository;
import problems.tradingwiththreads.services.*;

import java.io.IOException;

import static problems.tradingwiththreads.services.ChunkGenerator.generateChunksAndSubmitTask;

public class TradeMain {

//     public static HikariDataSource dataSource = DatabaseConnector.configureHikariCP();

    public static void main(String[] args) throws IOException {
//        HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
//        String chunkFileName = "";
        generateChunksAndSubmitTask();
//        TradeProcessor.submitTaskToThreads();
//        TradesRepository.insertIntoJournalTable();

    }
}
