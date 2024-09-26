package problems.tradeProcessing.customeinterface.files;

public interface TradeQueueWriterInterface {
    void writeTradeToQueue(String tradeId, int queueNumber); // Writes tradeId to appropriate queue
}
