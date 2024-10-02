package problems.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.model.RawPayload;
import problems.tradingwiththreads.repository.TradesRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static problems.tradingwiththreads.services.QueueDistributor.*;

public class ChunkProcessor implements Runnable {


    //ChunkProcessor will do these tasks - 1) read the file 2) insert into raw table 3) consult Hashmap 4) Add to Queue

    public String chunkFileName;
    static HikariDataSource dataSource;

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


    public static void processChunks(String chunkFileName) throws SQLException {
        System.out.println("processing chunkFileName = " + chunkFileName);
        try (Connection connection = dataSource.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(chunkFileName))) {
//            System.out.println("Processing file: " + chunkFileName);
            RawPayload rawPayload = new RawPayload();
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
                System.out.println("inserting data into rawPayload table");
                repository.insertInRawTable(rawPayload, connection);
//                System.out.println("---------------xxxxxxxx------------------------------------");

                System.out.println("done inserting...into raw payloads table " + columnInPayloads[0]);
                if (rawPayload.getStatus().equals("Valid")) {
                    int queueNumberForAccount = getQueueNumber(columnInPayloads[2]);
                    System.out.println("assigning trade_id " + columnInPayloads[0] + "  to queue number "+
                             + queueNumberForAccount);
                    //assign queue
                   assignQueueToAccountID(rawPayload.getTradeId(), queueNumberForAccount);


//                    System.out.println("------checking if queue is taking-------");
                }
            }

            //Thread.sleep(1000);
//            TradeProcessor.submitTaskToThreads(queueOne, queueTwo, queueThree);

            QueueDistributor.printMapAndQueue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startTradeProcessor() {

    }
}


