package problems.trading_3way_with_object.contract;

import problems.trading_3way_with_object.Trade;

import java.util.List;

public interface TradeReader {
    List<Trade> readTrades(String filePath, double errorThreshold) throws Exception;
}
