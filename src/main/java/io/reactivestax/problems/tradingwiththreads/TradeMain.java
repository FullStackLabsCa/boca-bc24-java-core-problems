package io.reactivestax.problems.tradingwiththreads;


import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.problems.tradingwiththreads.databaseconnector.DatabaseConnector;
import io.reactivestax.problems.tradingwiththreads.services.*;

import java.io.IOException;

import static io.reactivestax.problems.tradingwiththreads.services.ChunkGenerator.generateChunksAndSubmitTask;
import static io.reactivestax.problems.tradingwiththreads.services.QueueDistributor.*;

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
