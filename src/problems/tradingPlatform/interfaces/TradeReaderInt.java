package problems.tradingPlatform.interfaces;

import problems.tradingPlatform.models.Trade;

import java.util.List;

public interface TradeReaderInt {

    List<Trade> readTradeData(String filePath, double errorThreshold);
    Trade createTrade(String[] values, double errorThreshold);
}
