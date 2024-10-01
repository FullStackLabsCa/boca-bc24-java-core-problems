package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

import java.io.File;
import java.io.IOException;

public interface ChunkProcessor {
    void processChunk(File chunkFile) throws IOException;

    void insertToRawTableInDB(String trade_id, String status, String payload) throws IOException;

    Integer consultAccountToQueueMap(Long account_num, Integer queue_num);

    void insertToTradeQueue(int queue_num);

}
