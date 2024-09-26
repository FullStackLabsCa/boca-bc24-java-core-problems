package TradeProcessingMultiThreadingAssignment.MainRunner;

import TradeProcessingMultiThreadingAssignment.Service.TradeChunkGeneratorService;

import java.io.IOException;

public class MainRunner {

    public static void main(String[] args) {
        TradeChunkGeneratorService tradeProcessChunking = new TradeChunkGeneratorService();

        try {
            //tradeProcessChunking.fileLineCounter();
            tradeProcessChunking.chunkFileGenerator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
