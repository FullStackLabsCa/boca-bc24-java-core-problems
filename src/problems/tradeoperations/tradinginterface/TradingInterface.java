package problems.tradeoperations.tradinginterface;

import problems.tradeoperations.exception_files.HitErrorsThresholdException;

import java.util.List;

public interface TradingInterface {
    public void readFile(String filePath, Double effectiveThreshold) throws HitErrorsThresholdException;
    public int[] processBatchInsert(List<String[]> validTrades);

    }
