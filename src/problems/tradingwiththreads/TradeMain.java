package problems.tradingwiththreads;


import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.databaseconnector.DatabaseConnector;
import problems.tradingwiththreads.model.JournalEntryPOJO;
import problems.tradingwiththreads.repository.TradesRepository;
import problems.tradingwiththreads.services.*;

import java.io.IOException;

import static problems.tradingwiththreads.services.ChunkGenerator.generateChunksAndSubmitTask;
import static problems.tradingwiththreads.services.QueueDistributor.*;

public class TradeMain {

//     public static HikariDataSource dataSource = DatabaseConnector.configureHikariCP();

    public static void main(String[] args) throws IOException, InterruptedException {
        HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
//        String chunkFileName = "";
        generateChunksAndSubmitTask(dataSource);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(queueOne.size()+queueTwo.size()+queueThree.size());

        TradeProcessor.submitTaskToThreads(queueOne, queueTwo,queueThree, dataSource);


        Thread.sleep(5000);
   //     TradeProcessor.submitTaskToThreads();
//        TradesRepository.insertIntoJournalTable();

    }
}
