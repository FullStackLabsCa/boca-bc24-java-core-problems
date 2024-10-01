package trade.processing.multithreading;

import trade.processing.multithreading.service.TradeChunkGenerator;
import trade.processing.multithreading.utility.TaskSubmitter;

import java.io.File;

public class TradeProcessLauncher {
    public static void main(String[] args) {
        File file = new File("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trades.csv");
        TradeChunkGenerator tradeChunkGenerator = new TradeChunkGenerator();
        tradeChunkGenerator.generateAndSubmitAllChunks(file);
    }
}

