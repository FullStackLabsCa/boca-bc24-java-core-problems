package problems.jdbc.tradeprocessor.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.tradeprocessor.model.RawPayload;
import problems.jdbc.tradeprocessor.repository.TradeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ChunkProcessor implements Runnable {
    private final String filePath;
    HikariDataSource hikariDataSource;

    public ChunkProcessor(String filePath, HikariDataSource hikariDataSource) {
        this.filePath = filePath;
        this.hikariDataSource = hikariDataSource;
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
            RawPayload rawPayload = new RawPayload();
            String payload;
            while ((payload = reader.readLine()) != null) {
                rawPayload.setPayload(payload);
                String[] transaction = payload.split(",");
                rawPayload.setTradeId(transaction[0]);
                rawPayload.setStatus("valid");
                if (transaction.length != 7) {
                    rawPayload.setStatus("invalid");
                    rawPayload.setStatusReason("missing column(s)");
                }
                TradeRepository tradeRepository = new TradeRepository();
                // inserts to raw_payloads table
                tradeRepository.insertTradeRawPayload(rawPayload, connection);
                // inserts to concurrent hash map and get the queue number
                if (rawPayload.getStatus().equals("valid")) {
                    int queueNumber = QueueDistributor.getQueueNumber(transaction[2]);
                    // inserts to the queue number found in above step
                    QueueDistributor.giveToQueue(rawPayload.getTradeId(), queueNumber);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            e.printStackTrace();
        }
    }
}
