package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.TradeProcessing;
import multithread_trade_processing.model.Trade;
import multithread_trade_processing.repo.PayloadDatabaseRepo;
import multithread_trade_processing.repo.TradesDBRepo;

import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TradeProcessorTask implements Runnable, TradeProcessing {
    LinkedBlockingDeque<String> tradeIdQueue;

    private static final Logger logger = Logger.getLogger(TradeProcessorTask.class.getName());
    FileHandler fileHandler;

    public TradeProcessorTask(LinkedBlockingDeque<String> tradeIdQueue) {
        this.tradeIdQueue = tradeIdQueue;
    }

    public void configureLogger(){
        {
            try {
                fileHandler = new FileHandler("error.txt", true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
    }

    @Override
    public void run(){
//        configureLogger();
        while(true){
            String tradeID;
            try {
                tradeID = readTradeIdFromQueue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String payload = readPayloadFromRawDatabase(tradeID);
            Trade trade = validatePayloadAndCreateTrade(payload);
            if (trade != null) {
                if (validateBusinessLogic(trade).equals("Valid")) {
                    writeToJournalTable(trade);
                    writeToPositionsTable(trade);
                } else {
                    logger.info(trade.toString());
                }
            }
        }
    }

    @Override
    public String readTradeIdFromQueue() throws InterruptedException {
        return tradeIdQueue.takeFirst();
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

}
