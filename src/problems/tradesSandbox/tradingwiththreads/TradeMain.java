package problems.tradesSandbox.tradingwiththreads;


import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.databaseconnector.DatabaseConnector;
import problems.tradingwiththreads.services.*;

import java.io.IOException;

import static problems.tradingwiththreads.services.ChunkGenerator.generateChunksAndSubmitTask;
import static problems.tradingwiththreads.services.QueueDistributor.*;

public class TradeMain {

//     public static HikariDataSource dataSource = DatabaseConnector.configureHikariCP();

    public static void main(String[] args) throws IOException, InterruptedException {
        HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
        generateChunksAndSubmitTask(dataSource);
        TradeProcessor.submitTaskToThreads(queueOne, queueTwo,queueThree, dataSource);
        Thread.sleep(5000);


    }
}
