package multithreadingtrade.services;

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
        int tradeCounter = 0;
        while (!tradeQueue.isEmpty()) {
            try {
                processTrade();
                tradeCounter++;
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted: " + e.getMessage());
                break; // Stop the thread when it's interrupted.
            } catch (Exception e) {
                System.out.println("General Exception: " + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName()+",  tradeCounter :: "+tradeCounter);
    }

    private void processTrade() throws SQLException, InterruptedException {
        String tradeIdStoredInQueue = tradeQueue.take();
        try {
            insertDataToJournalAndPositionTable(tradeIdStoredInQueue);
        } catch (RuntimeException e) {
            System.out.println("CUSIP validation failed for Trade ID: " + tradeIdStoredInQueue + " - " + e.getMessage());
            // Continue processing other trades even if one trade fails CUSIP validation.
        }
    }

    public void insertDataToJournalAndPositionTable(String tradeIdFromQueue) {
        try {
            TradeRepo tradeRepo = new TradeRepo();
            String payload = tradeRepo.readPayloadFromRawTable(tradeIdFromQueue);
//            System.out.println(payload);
//            if (payload.isEmpty()) {
//                System.out.println("No payload");
//            }
            String[] readFromPayload = payload.split(",");
            boolean securityResult = tradeRepo.lookUpInSecuritiesTable(readFromPayload[3]);
            if (securityResult) {
                JournalEntry journalEntry = new JournalEntry();
                journalEntry.setTradeId(readFromPayload[0]);
                journalEntry.setAccountNumber(readFromPayload[2]);
                journalEntry.setCusip(readFromPayload[3]);
                journalEntry.setDirection(readFromPayload[4]);
                journalEntry.setQuantity(Integer.parseInt(readFromPayload[5]));
                journalEntry.setPostedStatus("Posted");
                tradeRepo.insertIntoJournalTable(journalEntry);
            }
            else {
              throw new RuntimeException("CUSIP not found: " + readFromPayload[3]);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
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


