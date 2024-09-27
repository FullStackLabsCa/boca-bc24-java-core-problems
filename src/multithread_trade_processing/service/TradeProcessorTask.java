package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.TradeProcessing;
import multithread_trade_processing.model.Trade;
import multithread_trade_processing.repo.PayloadDatabaseRepo;
import multithread_trade_processing.repo.TradesDBRepo;

import javax.management.RuntimeErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeProcessorTask implements Runnable, TradeProcessing {
    LinkedBlockingDeque<String> tradeIdQueue;

    public TradeProcessorTask(LinkedBlockingDeque<String> tradeIdQueue) {
        this.tradeIdQueue = tradeIdQueue;
    }

    @Override
    public void run(){
        String tradeID = readTradeIdFromQueue();
        String payload = readPayloadFromRawDatabase(tradeID);
        Trade trade = validatePayloadAndCreateTrade(payload);
        if(trade != null){
            if(validateBusinessLogic(trade).equals("Valid")){
                writeToJournalTable(trade);
                writeToPositionsTable(trade);
            }
        }
    }

    @Override
    public String readTradeIdFromQueue() {
        return tradeIdQueue.getFirst();
    }

    @Override
    public String readPayloadFromRawDatabase(String tradeID) {
        PayloadDatabaseRepo payloadDbAccess = new PayloadDatabaseRepo();
        return payloadDbAccess.readPayloadFromDB(tradeID);
    }

    @Override
    public Trade validatePayloadAndCreateTrade(String payload) {
        if(payload == null){
            throw new RuntimeException("Payload Validation Failed. Payload NULL!");
        }
        try {
            String[] payloadData = payload.split(",");
            String tradeId = payloadData[0];
            Date transactionTime = parseStringToDate(payloadData[1]);
            String accountNumber = payloadData[2];
            String cusip = payloadData[3];
            String activity = payloadData[4];
            int quantity = Integer.parseInt(payloadData[5]);
            double price = Double.parseDouble(payloadData[6]);

            return new Trade(tradeId, transactionTime, accountNumber, cusip, activity, quantity, price);

        } catch (NumberFormatException e) {
            throw new RuntimeException("Trade Object Creation Failed!!!");
        }
    }

    public Date parseStringToDate(String dateString){
        String format = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String validateBusinessLogic(Trade trade) {
        TradesDBRepo tradeDbAccess = new TradesDBRepo();

        return tradeDbAccess.checkIfValidCUSIP(trade);
    }

    @Override
    public void writeToJournalTable(Trade trade) {
        TradesDBRepo tradeDbAccess = new TradesDBRepo();
        tradeDbAccess.writeTradeToJournalTable(trade);
    }

    @Override
    public void writeToPositionsTable(Trade trade) {
        TradesDBRepo tradeDbAccess = new TradesDBRepo();
        tradeDbAccess.updatePositionsTable(trade);
    }

    public static void main(String[] args) {
        TradeProcessorTask test = new TradeProcessorTask(new LinkedBlockingDeque<>());

        String payload = test.readPayloadFromRawDatabase("TDB_00000025");
        Trade trade = test.validatePayloadAndCreateTrade(payload);
        if(trade != null){
            if(test.validateBusinessLogic(trade).equals("Valid")){
                test.writeToJournalTable(trade);
                test.writeToPositionsTable(trade);
            }
        }
    }
}
