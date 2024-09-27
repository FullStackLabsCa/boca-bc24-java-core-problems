package multithread_trade_processing.interfaces;

public interface TradesFileReading {

    chunksPathAndNumberOfChunks readFileAndCreateChunks(String filePath, String fileType);

}
