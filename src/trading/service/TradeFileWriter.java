package trading.service;

import trading.model.Trade;

import java.util.List;

public interface TradeFileWriter {
    int tradeFilewriter(List<Trade> tradeList);
}
