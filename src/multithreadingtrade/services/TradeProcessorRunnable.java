package multithreadingtrade.services;

import com.zaxxer.hikari.HikariDataSource;
import multithreadingtrade.databaseconnectivity.DatabaseConnection;
import multithreadingtrade.repo.TradeRepo;
import multithreadingtrade.trademodel.JournalEntry;

import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;


public class TradeProcessorRunnable implements Runnable {
    static String tradeIdFromQueue;
    static LinkedBlockingDeque<String> tradeQueue;
    HikariDataSource dataSource = DatabaseConnection.getDataSource();

    public TradeProcessorRunnable(LinkedBlockingDeque<String> tradeQueue) {
        this.tradeQueue = tradeQueue;
    }


    @Override
    public void run() {
        try {
            getTradeIdFromQueue();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void getTradeIdFromQueue() throws InterruptedException, SQLException {
        String tradeIdStoredInQueue = "";
        while (!tradeQueue.isEmpty()) {
            tradeIdStoredInQueue = tradeQueue.take();
            getDataFromTradePayloadsTable(tradeIdStoredInQueue);
        }
    }

    public static void getDataFromTradePayloadsTable(String tradeIdFromQueue) {
        try {
            TradeRepo tradeRepo = new TradeRepo();
            String payload = tradeRepo.readPayloadFromRawTable(tradeIdFromQueue);
            if(payload.isEmpty()) {
                System.out.println("No payload");
            }
            String[] readFromPayload = payload.split(",");

            //   boolean securityValidate = tradeRepo.
            //  System.out.println("Data: " + readFromPayload);

            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setAccountNumber(readFromPayload[2]);
            journalEntry.setCusip(readFromPayload[3]);
            journalEntry.setDirection(readFromPayload[4]);
            journalEntry.setQuantity(Integer.parseInt(readFromPayload[5]));
            journalEntry.setPostedStatus("Posted");

            TradeRepo.insertIntoJournalTable(journalEntry);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
