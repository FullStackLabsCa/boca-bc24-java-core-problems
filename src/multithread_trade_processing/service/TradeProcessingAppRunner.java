package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.chunksPathAndNumberOfChunks;

public class TradeProcessingAppRunner {

    public static void main(String[] args) {
        String folderPath = "/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/src/multithread_trade_processing";

        //This is 1 thread
        TradeProcessor processor = new TradeProcessor();
        processor.startTradeProcessingFromQueues();

        //This is another thread
        TradesFileReader reader = new TradesFileReader();
        chunksPathAndNumberOfChunks result =  reader.readFileAndCreateChunks(folderPath+"/trades.csv", null);

        //This is another thread
        ChunkProcessor chunkProcessor = new ChunkProcessor();
        chunkProcessor.startChunkProcessorPool(result.folderPath(), result.numberOfFiles());

    }
}
