package problems.tradingwiththreads;


import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.services.ChunkProcessor;
import problems.tradingwiththreads.services.QueueDistributor;

import java.io.IOException;

import static problems.tradingwiththreads.services.ChunkGenerator.generateChunksAndSubmitTask;

public class TradeMain {

//     public static HikariDataSource dataSource = DatabaseConnector.configureHikariCP();

    public static void main(String[] args) throws IOException {
//        HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
//        String chunkFileName = "";
        generateChunksAndSubmitTask();
    }
}
