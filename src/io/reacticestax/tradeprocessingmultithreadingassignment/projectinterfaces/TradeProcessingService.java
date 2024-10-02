package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

import java.io.IOException;

public interface TradeProcessingService {
    void insertToTradePayloadTableInDB(String trade_id, String status, String payload) throws IOException;


}
