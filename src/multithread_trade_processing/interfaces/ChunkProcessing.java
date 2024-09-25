package multithread_trade_processing.interfaces;

public interface ChunkProcessing {

    void writePayloadToRawDatabase(String payload);
    void getQueueMapping(String accountNumber);
    void writeToQueue(String tradeID);

}
