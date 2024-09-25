package multithread_trade_processing.interfaces;

import multithread_trade_processing.model.Trade;

public interface TradeProcessing {

    String readFromQueue(String queueID);
    String readPayloadFromRawDatabase(String tradeID);
    String validatePayload(String payload);
    Trade createTradeObject(String payload);
    String validateBusinessLogic(Trade trade);
    void writeToJournalTable(Trade trade);
    void writeToPositionsTable(Trade trade);

}
