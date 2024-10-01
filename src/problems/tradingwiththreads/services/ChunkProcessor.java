package problems.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.DatabaseConnector;
import problems.tradingwiththreads.model.RawPayloadPOJO;
import problems.tradingwiththreads.model.TradePOJO;
import problems.tradingwiththreads.repository.TradesRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import static problems.tradingwiththreads.services.QueueDistributor.*;

public class ChunkProcessor implements Runnable {


    //ChunkProcessor will do these tasks - 1) read the file 2) insert into raw table 3) consult Hashmap 4) Add to Queue

    public String chunkFileName;
    HikariDataSource dataSource;

    public ChunkProcessor(String chunkFileName, HikariDataSource dataSource) {
        this.chunkFileName = chunkFileName;
        this.dataSource = dataSource;
    }


    @Override
    public void run() {
        try {
            processChunks(chunkFileName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void processChunks(String chunkFileName) throws SQLException {
//        System.out.println("called====");
        try (Connection connection = dataSource.getConnection();
            BufferedReader reader = new BufferedReader(new FileReader(chunkFileName))) {
//            System.out.println("Processing file: " + chunkFileName);
            RawPayloadPOJO rawPayload = new RawPayloadPOJO();
            TradesRepository repository = new TradesRepository();
            String payloadData;
            while ((payloadData = reader.readLine()) != null) {
                rawPayload.setPayload(payloadData);
                String[] columnInPayloads = payloadData.split(",");
                rawPayload.setTradeId(columnInPayloads[0]);

                if (columnInPayloads.length < 7) {
                    rawPayload.setStatus("Invalid");
                    rawPayload.setStatusReason("Fields are missing data");
                }
//                System.out.println("inserting data");
                repository.insertInRawTable(rawPayload, connection);
//                System.out.println("---------------xxxxxxxx------------------------------------");

                if (rawPayload.getStatus().equals("Valid")) {
                    int queueNumberForAccount = getQueueNumber(columnInPayloads[2]);
                    //assign queue
                    assignQueueToAccountID(rawPayload.getTradeId(), queueNumberForAccount);
//                    System.out.println("1 -"+queueOne.size());
//                    System.out.println("2 -"+queueTwo.size());
//                    System.out.println("3 -"+queueThree.size());
                }
            }


            QueueDistributor.printMapAndQueue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


