package multithreadingtrade.services;

import multithreadingtrade.repo.TradeRepo;
import multithreadingtrade.trademodel.RawPayLoad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;


/**
 * It takes the chunk file and insert into the raw
 */

public class ChunkProcessor implements Runnable {
    private final String filePath;

    public ChunkProcessor(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            processChunk();
        } catch (SQLException e) {
            System.out.println("e.getMessage(ChunkProcessor Run) = " + e.getMessage());
        }
    }


    /**
     * Step 1: Read file line by line
     * Step 2: Validate the record if it's a good record
     * Step 3: When all validations are done then publish it Queue
     * Step 4: Publish to Queue
     *          it checks the map if the account number is present in the queue or not , if it's not then it should randomly assign the queue
     *          if it's the account no then should return the queue number
     *
     *
     *
     */

    public void processChunk() throws SQLException {
   //     int totalChunksPublished = 0;
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
                    rawPayload.setStatusReason("Field does not exist.");
                }
                TradeRepo tradeRepo = new TradeRepo();
                tradeRepo.insertIntoRawPayLoad(rawPayload);

                int queueNumber = QueueDistributor.validateAccountNumberIntoTheQueue(fields[2]);
                QueueDistributor.writesToQueues(fields,queueNumber);
                //  add the account to the queue
//                int queueNumber = QueueDistributor.getRandomNumber();

                //field2 is the account number
               // QueueDistributor.accountQueueMap.put(fields[2], );
                // making a queue
      //          QueueDistributor.writesToQueues(fields,QueueDistributor.getRandomNumber());
       //         totalChunksPublished++;
                // inserting to raw table
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
            //System.out.println(e.getMessage());
        }
    //    System.out.println("No of chunks published "+totalChunksPublished+", by thread "+Thread.currentThread().getName()+", filePath "+filePath);
    }

}

