package multithread_trade_processing.interfaces;

public interface TradeReading {

    chunksPathAndNumberOfChunks readFileAndCreateChunks(String filePath, String fileType);

}
