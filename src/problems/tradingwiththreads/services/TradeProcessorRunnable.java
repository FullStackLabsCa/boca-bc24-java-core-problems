package problems.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.databaseconnector.DatabaseConnector;
import problems.tradingwiththreads.model.JournalEntryPOJO;
import problems.tradingwiththreads.model.PositionsPOJO;
import problems.tradingwiththreads.repository.TradesRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessorRunnable implements Runnable {
    LinkedBlockingDeque<String> queue;
    static HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
    private String name;

    public TradeProcessorRunnable(LinkedBlockingDeque<String> queue, String name) {
        this.queue = queue;
        this.name = name;



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
        while (!this.queue.isEmpty()) {
            Thread.sleep(1000);
            try {
                String tradeIdFromQueue = this.queue.take();
                TradesRepository repositoryForGettingPayload = new TradesRepository();
                String payloadFromRawTable = repositoryForGettingPayload.getPayloadFromRawTable(tradeIdFromQueue, dataSource.getConnection());
                String[] columnsInPayload = payloadFromRawTable.split(",");
                JournalEntryPOJO journalEntryPOJO = new JournalEntryPOJO();

                journalEntryPOJO.setAccountNumber(columnsInPayload[2]);
//                journalEntryPOJO.setTransactionTime(Timestamp.valueOf(LocalDateTime.parse(columnsInPayload[1], DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss"))));
                journalEntryPOJO.setCusip(columnsInPayload[3]);
//                journalEntryPOJO.setSecurityId(columnsInPayload[3]);
                journalEntryPOJO.setDirection(columnsInPayload[4]);
                journalEntryPOJO.setQuantity(columnsInPayload[5]);
//                journalEntryPOJO.setPostedStatus(columnsInPayload[6]);

                PositionsPOJO positionsPOJO = new PositionsPOJO();

                positionsPOJO.setAccountNumber(columnsInPayload[2]);
                positionsPOJO.setCusip(columnsInPayload[3]);
                positionsPOJO.setPosition(columnsInPayload[4]);


                TradesRepository.insertIntoJournalTable(journalEntryPOJO, dataSource.getConnection());

                System.out.println("Processor " + name + ", queue size : " + this.queue.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    }

