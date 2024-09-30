package problems.tradingplatform.maininterface;

import problems.tradingplatform.customexceptions.HitErrorsThresholdException;

import java.sql.SQLException;
import java.util.List;

public interface TradeInterface {
    public void tradeReadingOperation(String filePath, double threshold) throws HitErrorsThresholdException;

    public void TradeWritingDAO(List<String[]> validRows, double threshold) throws SQLException;

}
