package problems.jdbc.tradeprocessor;

import problems.jdbc.tradeprocessor.service.TradeProcessorService;

public class TradeProcessorRunner {
    public static void main(String[] args) {
        TradeProcessorService tradeProcessorService = new TradeProcessorService();
        tradeProcessorService.processTrade("/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/src" +
                "/problems/jdbc/tradeprocessor/assets/trades.csv");
    }
}
