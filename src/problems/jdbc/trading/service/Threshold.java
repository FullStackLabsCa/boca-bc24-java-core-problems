package problems.jdbc.trading.service;

import problems.jdbc.trading.exception.InvalidThresholdValueException;

public interface Threshold {
    double fetchThresholdValue();

    boolean isThresholdExceeded();

    double validateThreshold(String thresholdString) throws InvalidThresholdValueException;
}
