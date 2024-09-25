package problems.jdbc.trading.service;

import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;

public interface ThresholdInterface {
    Trade createTradeRecord(String line);

    double fetchThresholdValue();

    boolean isThresholdExceeded(ErrorChecking errorChecking);
}
