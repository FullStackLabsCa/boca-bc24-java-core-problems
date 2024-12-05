package problems.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.model.JournalEntry;
import problems.tradingwiththreads.model.Positions;
import problems.tradingwiththreads.repository.TradesRepository;

import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessorRunnable implements Runnable {
    LinkedBlockingDeque<String> queue;
    boolean insertedIntoJournalEntry = false;
 //   static HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
 static HikariDataSource dataSource;
    private String name;

    public TradeProcessorRunnable(LinkedBlockingDeque<String> queue, HikariDataSource dataSource) {
        this.queue = queue;
        this.name = name;
        this.dataSource = dataSource;


    }

    @Override
    public void run() {
        try {
            processTrades();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void processTrades() throws InterruptedException {
        System.out.println(queue.size());
        System.out.println("starting to processs trades");
        while (true) {
            //    Thread.sleep(1000);
            int journalEntryCounter=0;
            try {
                String tradeIdFromQueue = this.queue.take();
                this.insertedIntoJournalEntry = false;
                String[] columnsInPayload = doJournalEntryWork(tradeIdFromQueue);
                if(this.insertedIntoJournalEntry) doPositionsWork(columnsInPayload);

                journalEntryCounter++;
                System.out.println("journalEntryCounter = " + journalEntryCounter + " for accountNumber" + columnsInPayload[0]);


                System.out.println("Processor " + name + ", queue size : " + this.queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    private void doPositionsWork(String[] columnsInPayload) throws SQLException {
        /**
         * step1 - do a select in positions table for acc and security
         *          if found
         *              step2: do update
         *          if not found
         *              step3: do insert
         *
         *
         * do insert
         *              do update
         *
         */

        TradesRepository repositoryForRepository = new TradesRepository();
        String rawTablePayload = repositoryForRepository.getPayloadFromRawTable(columnsInPayload[0], dataSource.getConnection());
        String[] columns = rawTablePayload.split(",");
        Positions positionsPOJO = new Positions();

        positionsPOJO.setAccountNumber(columns[2]);
        positionsPOJO.setCusip(columns[3]);
        positionsPOJO.setPosition(columns[4]);
        positionsPOJO.setQuantity(columns[5]);
        boolean checkPosition = TradesRepository.checkForPositions(dataSource.getConnection(), positionsPOJO);
        if(checkPosition){
            TradesRepository.updatePositions(dataSource.getConnection(), positionsPOJO);
            System.out.println("Updating into positions table");
        } else {
            TradesRepository.insertIntoPositionsTable(positionsPOJO, dataSource.getConnection());
            System.out.println("Inserting into positions table");
        }


    }

    private String[] doJournalEntryWork(String tradeIdFromQueue) throws SQLException {
        TradesRepository repositoryForGettingPayload = new TradesRepository();
        String payloadFromRawTable = repositoryForGettingPayload.getPayloadFromRawTable(tradeIdFromQueue, dataSource.getConnection());
        String[] columnsInPayload = payloadFromRawTable.split(",");
        JournalEntry journalEntryPOJO = new JournalEntry();

        journalEntryPOJO.setAccountNumber(columnsInPayload[2]);
        journalEntryPOJO.setCusip(columnsInPayload[3]);
        journalEntryPOJO.setDirection(columnsInPayload[4]);
        journalEntryPOJO.setQuantity(Integer.parseInt(columnsInPayload[5]));
        this.insertedIntoJournalEntry = TradesRepository.insertIntoJournalTable(journalEntryPOJO, dataSource);
        return columnsInPayload;
    }

}

//                journalEntryPOJO.setTransactionTime(Timestamp.valueOf(LocalDateTime.parse(columnsInPayload[1], DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss"))));
