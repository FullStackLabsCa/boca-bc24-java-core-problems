package problems.tradingPlatform.interfaces;

import problems.tradingPlatform.models.Trade;

import java.util.List;

public interface TradeReaderInt {

    List<Trade> readTradeData(String filePath);
    Trade createTrade(String[] values,int row);
}
