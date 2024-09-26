package multithread_trade_processing.interfaces;

public interface ChunkProcessing {

    void processPayload(String filePath);
    String checkPayloadValidity(String payload);
    tradeIdAndAccNum getIdentifierFromPayload(String payload);
    void writePayloadToPayloadDatabase(String tradeID, String tradeStatus, String payload);
    int getQueueMapping(String accountNumber);
    void writeToQueue(String tradeID, int queueID);

}
