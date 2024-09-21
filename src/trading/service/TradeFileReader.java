package trading.service;

import trading.model.Trade;

import java.io.File;
import java.util.List;

public interface TradeFileReader {
    List<Trade> tradeFileReader(File file);
}
