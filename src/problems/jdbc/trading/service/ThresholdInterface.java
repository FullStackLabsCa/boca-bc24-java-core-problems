package problems.jdbc.trading.service;

import problems.jdbc.trading.exception.InvalidThresholdValueException;
import problems.jdbc.trading.model.Trade;

public interface ThresholdInterface {
    Trade createTradeRecord(String line);

    double fetchThresholdValue();

    boolean isThresholdExceeded();

    double validateThreshold(String thresholdString) throws InvalidThresholdValueException;
}
