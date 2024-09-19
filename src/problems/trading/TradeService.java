package problems.trading;

import problems.trading.exceptions.HitErrorsThresholdException;

import java.util.concurrent.LinkedBlockingDeque;

public class TradeService {
    public static int errorCount = 0;
    public static double errorThreshold = 0;

    public static void checkingThreshold(LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) {
        if (TradeService.errorCount > (TradeService.errorThreshold * tradingTransactionDeQueue.size()) / 100) {
            throw new HitErrorsThresholdException("Errors exceeded threshold limit");
        }
    }
}
