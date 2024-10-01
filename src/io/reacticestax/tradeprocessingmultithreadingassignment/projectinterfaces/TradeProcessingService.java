package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

import java.io.File;
import java.io.IOException;

public interface TradeProcessingService {
    void insertToRawTableInDB(long trade_id, String status, String payload) throws IOException;
}
