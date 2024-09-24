package problems.trading_3way_with_object.contract;

import problems.trading_3way_with_object.Trade;

import java.util.List;

public interface TradeWriter {
    int writeTrades(List<Trade> trades, double errorThreshold) throws Exception;
}
