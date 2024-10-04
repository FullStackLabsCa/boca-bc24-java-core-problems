package io.reactivestax.services;

import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.model.RawPayloadSandbox;
import io.reactivestax.repository.TradesRepositorySandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import static io.reactivestax.services.QueueDistributorSandbox.*;

public class ChunkProcessorSandbox implements Runnable {

    public String chunkFileName;
    static HikariDataSource dataSource;

    public ChunkProcessorSandbox(String chunkFileName, HikariDataSource dataSource) {
        this.chunkFileName = chunkFileName;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        try {
            processChunks(chunkFileName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void processChunks(String chunkFileName) throws SQLException {
        System.out.println("processing chunkFileName = " + chunkFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFileName))) {

            RawPayloadSandbox rawPayload = new RawPayloadSandbox();
            TradesRepositorySandbox repository = new TradesRepositorySandbox();
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
                repository.insertInRawTable(rawPayload, dataSource);

                System.out.println("done inserting...into raw payloads table " + columnInPayloads[0]);
                if (rawPayload.getStatus().equals("Valid")) {
                    int queueNumberForAccount = getQueueNumber(columnInPayloads[2]);
                    System.out.println("assigning trade_id " + columnInPayloads[0] + "  to queue number " + +queueNumberForAccount);

                    assignQueueToAccountID(rawPayload.getTradeId(), queueNumberForAccount);
                    System.out.println("========================== assignQueueToAccountId =====================");
                }
            }

            QueueDistributorSandbox.printMapAndQueue();

        } catch (IOException | InterruptedException e) {
            System.err.println("Thread was interrupted while adding to the queue - " + e.getMessage());
            e.printStackTrace();
        }
    }

}


