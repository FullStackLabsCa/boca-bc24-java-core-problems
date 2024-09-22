package problems.tradeOperations.tradingInterface;

import problems.tradeOperations.exceptionFiles.HitErrorsThresholdException;

import java.util.List;

public interface TradingInterface {
    public void readFile(String filePath, Double effectiveThreshold) throws HitErrorsThresholdException;
    public int[] processBatchInsert(List<String[]> validTrades);

    }
