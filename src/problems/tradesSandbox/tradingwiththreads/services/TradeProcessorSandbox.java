package problems.tradesSandbox.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;

import problems.tradesSandbox.tradingwiththreads.model.JournalEntrySandbox;
import problems.tradesSandbox.tradingwiththreads.model.PositionsSandbox;
import problems.tradesSandbox.tradingwiththreads.repository.TradesRepositorySandbox;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;


public class TradeProcessorSandbox implements Runnable {
    LinkedBlockingQueue<String> linkedBlockingQueue;
    private HikariDataSource dataSource;

    public TradeProcessorSandbox(LinkedBlockingQueue<String> linkedBlockingQueue, HikariDataSource dataSource) {
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.dataSource = dataSource;
        System.out.println(dataSource);
    }

    @Override
    public void run() {
        while(true) {
            try {
                getTradeIdFromQueue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getTradeIdFromQueue() throws InterruptedException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String tradeIdFromQueue = "";
            System.out.println("linkedBlockingQueue size===" + linkedBlockingQueue.size());
            while (!linkedBlockingQueue.isEmpty()) {
                tradeIdFromQueue = this.linkedBlockingQueue.take();
                System.out.println("===== Taking tradeId from Queue ==== " + tradeIdFromQueue);
                processTrades(tradeIdFromQueue, connection);
            }
        }
    }

    public void processTrades(String tradeIdFromQueue, Connection connections) throws InterruptedException, SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            //step1
            processJournalEntries(tradeIdFromQueue);
            //step2
            processPositions(tradeIdFromQueue);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connections.rollback();
            e.printStackTrace();
        }

//        trade_id,transaction_time,account_number,cusip,activity,quantity,price
//TDB_00000000,2024-09-19 22:16:18,TDB_CUST_5214938,V,SELL,683,638.02
    }

    private void processJournalEntries(String tradeIdFromQueue) {
        TradesRepositorySandbox repositoryForRepository = new TradesRepositorySandbox();
        try (Connection connection = dataSource.getConnection()) {
            String rawTablePayload = repositoryForRepository.getPayloadFromRawTableSandbox(tradeIdFromQueue, connection);
            String[] columns = rawTablePayload.split(",");
            JournalEntrySandbox journalEntryPOJO = new JournalEntrySandbox();

            journalEntryPOJO.setJournalAccountID(columns[2]);
            journalEntryPOJO.setJournalCusip(columns[3]);
            journalEntryPOJO.setJournalDirection(columns[4]);
            journalEntryPOJO.setJournalQuantity(Integer.parseInt(columns[5]));
            repositoryForRepository.insertIntoJournalTable(journalEntryPOJO, connection);
             //isnert journal entry is done here
            System.out.println("_____________UPDATING POSITIONS TABLE_________");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void processPositions(String tradeIdFromQueue) {
        TradesRepositorySandbox repositoryForRepository = new TradesRepositorySandbox();
        JournalEntrySandbox journalEntrySandbox = new JournalEntrySandbox();
        try (Connection connection = dataSource.getConnection()) {
            //positions work starts here
            PositionsSandbox positionsSandbox = new PositionsSandbox();
            positionsSandbox.setPositionAccountId(journalEntrySandbox.getJournalAccountID());
            positionsSandbox.setPositionCusip(journalEntrySandbox.getJournalCusip());
            positionsSandbox.setPositionPosition(journalEntrySandbox.getJournalDirection());
            System.out.println("called before insert statement");

            System.out.println("_________INSERTED IN JOURNAL ENTRY TABLE_______");

            repositoryForRepository.doUpdateOrInsert(connection, positionsSandbox, journalEntrySandbox);
            System.out.println("_____________UPDATING POSITIONS TABLE_________");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

