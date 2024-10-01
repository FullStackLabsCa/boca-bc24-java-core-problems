package problems.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.databaseconnector.DatabaseConnector;
import problems.tradingwiththreads.model.JournalEntryPOJO;
import problems.tradingwiththreads.repository.TradesRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessorRunnable implements Runnable {
    LinkedBlockingDeque<String> queue;
    HikariDataSource dataSource = DatabaseConnector.configureHikariCP();

    public TradeProcessorRunnable(LinkedBlockingDeque<String> queue) {
        this.queue = queue;


    }

    @Override
    public void run() {
        while (!this.queue.isEmpty()) {
            try {
                String tradeIdFromQueue = this.queue.take();
                TradesRepository repositoryForGettingPayload = new TradesRepository();
                String payloadFromRawTable = repositoryForGettingPayload.getPayloadFromRawTable(tradeIdFromQueue, dataSource.getConnection());
                String[] columnsInPayload = payloadFromRawTable.split(",");
                JournalEntryPOJO journalEntryPOJO = new JournalEntryPOJO();

                journalEntryPOJO.setAccountNumber(columnsInPayload[0]);
//                journalEntryPOJO.setTransactionTime(Timestamp.valueOf(LocalDateTime.parse(columnsInPayload[1], DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss"))));
                journalEntryPOJO.setCusip(columnsInPayload[1]);
//                journalEntryPOJO.setSecurityId(columnsInPayload[3]);
                journalEntryPOJO.setDirection(columnsInPayload[2]);
                journalEntryPOJO.setQuantity(columnsInPayload[3]);
//                journalEntryPOJO.setPostedStatus(columnsInPayload[6]);

                TradesRepository.insertIntoJournalTable(journalEntryPOJO, dataSource.getConnection());






            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
