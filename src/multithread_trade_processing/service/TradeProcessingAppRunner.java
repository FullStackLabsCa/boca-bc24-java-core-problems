package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.chunksPathAndNumberOfChunks;

public class TradeProcessingAppRunner {

    public static void main(String[] args) {
        String folderPath = "/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/src/multithread_trade_processing";

        TradeProcessor processor = new TradeProcessor();
        processor.startTradeProcessingFromQueues();

        TradesFileReader reader = new TradesFileReader();
        chunksPathAndNumberOfChunks result =  reader.readFileAndCreateChunks(folderPath+"/trades.csv", null);

        ChunkProcessor chunkProcessor = new ChunkProcessor();
        chunkProcessor.startChunkProcessorPool(result.folderPath(), result.numberOfFiles());

    }
}
