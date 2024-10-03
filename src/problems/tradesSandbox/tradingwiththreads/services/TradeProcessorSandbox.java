package problems.tradesSandbox.tradingwiththreads.services;

import com.zaxxer.hikari.HikariDataSource;

import problems.tradesSandbox.tradingwiththreads.model.JournalEntrySandbox;
import problems.tradesSandbox.tradingwiththreads.model.PositionsSandbox;
import problems.tradesSandbox.tradingwiththreads.repository.TradesRepositorySandbox;
import problems.tradingwiththreads.repository.TradesRepository;

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
        while (true) {
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
        while (!linkedBlockingQueue.isEmpty()) {
            System.out.println("linkedBlockingQueue size===" + linkedBlockingQueue.size());
            String tradeIdFromQueue = this.linkedBlockingQueue.take();
            System.out.println("===== Taking tradeId from Queue ==== " + tradeIdFromQueue);
            //////
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                processTrades(tradeIdFromQueue, connection);
            } catch (Throwable throwable) {
                throwable.printStackTrace();

                if (connection != null) {
                    System.out.println(" rolling back the trasaction for tradeId" + tradeIdFromQueue);
                    connection.rollback();
                }
                throw throwable;
            }
        }
    }

    public void processTrades(String tradeIdFromQueue, Connection connection) throws InterruptedException, SQLException {
        connection.setAutoCommit(false);
        TradesRepositorySandbox tradesRepositorySandbox = new TradesRepositorySandbox();
        String rawTablePayload = tradesRepositorySandbox.getPayloadFromRawTableSandbox(tradeIdFromQueue, connection);
        String[] splittedTrade = rawTablePayload.split(",");
        if (TradesRepositorySandbox.lookupInSecuritiesTable(connection, splittedTrade[3])) {
            //step1
            JournalEntrySandbox journalEntrySandbox = processJournalEntries(tradeIdFromQueue, connection);
            //step2
            processPositions(journalEntrySandbox, connection);
        }
        connection.commit();
        connection.setAutoCommit(true);
        connection.close();
    }

    private JournalEntrySandbox processJournalEntries(String tradeIdFromQueue, Connection connection) throws SQLException {
        TradesRepositorySandbox repositoryForRepository = new TradesRepositorySandbox();

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
        return journalEntryPOJO;
    }

    private void processPositions(JournalEntrySandbox journalEntrySandbox, Connection connection) throws SQLException {
        TradesRepositorySandbox tradesRepositorySandbox = new TradesRepositorySandbox();
        //positions work starts here
        PositionsSandbox positionsSandbox = new PositionsSandbox();
        positionsSandbox.setPositionAccountId(journalEntrySandbox.getJournalAccountID());
        positionsSandbox.setPositionCusip(journalEntrySandbox.getJournalCusip());
        positionsSandbox.setPositionPosition(journalEntrySandbox.getJournalQuantity());
        System.out.println("called before insert statement");
        System.out.println("_________INSERTED IN JOURNAL ENTRY TABLE_______");

        tradesRepositorySandbox.doUpdateOrInsert(connection, positionsSandbox, journalEntrySandbox);
        System.out.println("_____________UPDATING POSITIONS TABLE_________");

    }
}

