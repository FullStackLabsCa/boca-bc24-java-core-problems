package multithread_trade_processing.interfaces;

public interface TradeReading {

    void readFileAndCreateChunks(String filePath, String fileType);

}
