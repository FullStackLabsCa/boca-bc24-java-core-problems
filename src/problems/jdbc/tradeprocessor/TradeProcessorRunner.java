package problems.jdbc.tradeprocessor;

import problems.jdbc.tradeprocessor.service.ChunkProcessorService;

public class TradeProcessorRunner {
    public static void main(String[] args) {
        ChunkProcessorService chunkProcessorService = new ChunkProcessorService();
        chunkProcessorService.processTrade("/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src" +
                "/io/reactivestax/problems/jdbc/tradeprocessor/assets/trades.csv");
    }
}
