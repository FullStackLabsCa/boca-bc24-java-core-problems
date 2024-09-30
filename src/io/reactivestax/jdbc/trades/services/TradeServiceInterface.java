package io.reactivestax.jdbc.trades.services;

import io.reactivestax.jdbc.trades.exceptions.HitErrorsThresholdException;
import io.reactivestax.jdbc.trades.exceptions.InvalidThresholdValueException;

public interface TradeServiceInterface {
    void userInput();

    void validateThresholdIsValid() throws InvalidThresholdValueException;

    void readThresholdLimitFromProperties();

    void validateThresholdLimitReached() throws HitErrorsThresholdException;

    void calculateThresholdPercent();

    boolean readCSV(String path);

    void printSummary();
}
