package multithread_trade_processing.interfaces;

import multithread_trade_processing.model.Trade;

public interface TradeProcessing {

    String readTradeIdFromQueue();
    String readPayloadFromRawDatabase(String tradeID);
    Trade validatePayloadAndCreateTrade(String payload);
    String validateBusinessLogic(Trade trade);
    void writeToJournalTable(Trade trade);
    void writeToPositionsTable(Trade trade);

}
