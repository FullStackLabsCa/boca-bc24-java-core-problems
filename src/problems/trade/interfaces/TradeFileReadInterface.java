package problems.trade.interfaces;

import problems.trade.exceptions.HitErrorsThresholdException;
import problems.trade.exceptions.InvalidThresholdRuntimeException;
import problems.trade.model.Trade;
import java.util.List;

public interface TradeFileReadInterface {
    List<Trade> readTradeDataFromCSV(String filePath)  throws HitErrorsThresholdException;
}
