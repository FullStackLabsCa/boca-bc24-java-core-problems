package problems.jdbc.trade;

import java.io.IOException;

public class MainRunner {
    public static void main(String[] args) throws Exception {
        TradeFileReaderImpl tradeFileReader = new TradeFileReaderImpl();
        String filePath = "/Users/Suraj.Adhikari/sources/student-mode-programs/boca-bc24-java-core-problems/src/problems/jdbc/trade/trade_data.csv";
        tradeFileReader.processFile(filePath);
    }
}
