package multithreadingtrade.services;

import com.zaxxer.hikari.HikariDataSource;
import multithreadingtrade.databaseconnectivity.DatabaseConnection;
import multithreadingtrade.repo.TradeRepo;
import multithreadingtrade.trademodel.RawPayLoad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;


public class ChunkProcessor implements Runnable {
    private final String filePath;
    static HikariDataSource hikariDataSource = DatabaseConnection.getDataSource();

    public ChunkProcessor(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            processChunk();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void processChunk() throws SQLException {
        Connection connection = hikariDataSource.getConnection();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            RawPayLoad rawPayload = new RawPayLoad();
            String payload;

            while ((payload = reader.readLine()) != null) {
                rawPayload.setPayLoads(payload);
                String[] fields = payload.split(",");
                rawPayload.setTradeID(fields[0]);
                rawPayload.setStatus("valid");

                if (fields.length != 7) {
                    rawPayload.setStatus("invalid");
                    rawPayload.setStatusReason("column does not exist.");
                }
                TradeRepo tradeRepo = new TradeRepo();
                //  add the account to the queue
                Integer queueNumber = QueueDistributor.getRandomNumber();
//                System.out.println(queueNumber);
                QueueDistributor.accountQueueMap.put(fields[2], queueNumber);
                //making a queue

                QueueDistributor.writesToQueues(queueNumber, fields);
                //inserting to raw table
                tradeRepo.insertIntoRawPayLoad(rawPayload);

            }
//            QueueDistributor.printTotalNumberOfQueue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

