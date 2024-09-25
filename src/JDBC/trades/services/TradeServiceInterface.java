package JDBC.trades.services;

import JDBC.trades.exceptions.HitErrorsThresholdException;
import JDBC.trades.exceptions.InvalidThresholdValueException;

public interface TradeServiceInterface {
    void userInput();
    void validateThresholdIsValid() throws InvalidThresholdValueException;
    void readThresholdLimitFromProperties();
    void validateThresholdLimitReached() throws HitErrorsThresholdException;
    void calculateThresholdPercent();
    boolean readCSV(String path);
    void printSummary();
}
