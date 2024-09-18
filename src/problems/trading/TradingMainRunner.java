package problems.trading;

import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradingMainRunner {
    public static LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue = new LinkedBlockingDeque<>(5000);

    public static void main(String[] args) throws Exception {
        // read file and load data into LinkedBlockingDeque
        String filePath = "/Users/Gaurav.Manchanda/src/fullstack/trade_data_faulty.csv";
        tradingTransactionDeQueue = FileProcessor.readTransactionFileAndWriteToQueue(filePath, tradingTransactionDeQueue);

        TradeRepository.insertQuery(tradingTransactionDeQueue);
    }
}
