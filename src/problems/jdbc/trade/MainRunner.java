package problems.jdbc.trade;

import java.io.IOException;

public class MainRunner {
    public static void main(String[] args) throws IOException, InvalidInputException {
        TradeFileParser.processFile("/Users/Suraj.Adhikari/sources/student-mode-programs/boca-bc24-java-core-problems/src/problems/jdbc/trade/trade_data.csv");
    }
}
