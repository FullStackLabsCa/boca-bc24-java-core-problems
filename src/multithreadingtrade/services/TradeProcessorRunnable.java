package multithreadingtrade.services;

import com.zaxxer.hikari.HikariDataSource;
import multithreadingtrade.databaseconnectivity.DatabaseConnection;
import multithreadingtrade.repo.TradeRepo;
import multithreadingtrade.trademodel.JournalEntry;

import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;


public class TradeProcessorRunnable implements Runnable {
    static LinkedBlockingDeque<String> tradeQueue;

    public TradeProcessorRunnable(LinkedBlockingDeque<String> tradeQueue) {
        this.tradeQueue = tradeQueue;
    }


    @Override
    public void run() {
        try {
            getTradeIdFromQueue();
        } catch (SQLException | InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
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
            if (payload.isEmpty()) {
                System.out.println("No payload");
            }
            String[] readFromPayload = payload.split(",");

//
//            JournalEntry journalEntry = new JournalEntry();
//            journalEntry.setAccountNumber(readFromPayload[2]);
//            journalEntry.setCusip(readFromPayload[3]);
//            journalEntry.setDirection(readFromPayload[4]);
//            journalEntry.setQuantity(Integer.parseInt(readFromPayload[5]));
//            journalEntry.setPostedStatus("Posted");
//
//            TradeRepo.insertIntoJournalTable(journalEntry);

//            Positions positions = new Positions();
//            positions.setAccountNumber(readFromPayload[2]);
//            positions.setCusip(readFromPayload[3]);
//            positions.setPosition(readFromPayload[4]);
//            positions.setQuantity(Integer.parseInt(readFromPayload[5]));

//            writePositionToDB(positions);


        } catch (SQLException e) {

            System.out.println("e.getMessage(getDataFromTradePayloadsTable) = " + e.getMessage());
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(e);
        }


    }

//    public static void writePositionToDB(Positions positions) throws SQLException {
//        int version = TradeRepo.getPositionVersion(positions);
//        try {
//            if (version == -1) {
//                TradeRepo.writeToPositionTable(positions);
//            }
//            TradeRepo.updatePositionTable(positions);
//        } catch (SQLException e) {
//            System.out.println("e.getMessage(writePositionToDB) = " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
}


