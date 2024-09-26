package jdbc.trades.services;

import jdbc.trades.exceptions.HitErrorsThresholdException;
import jdbc.trades.exceptions.InvalidThresholdValueException;

public interface TradeServiceInterface {
    void userInput();

    void validateThresholdIsValid() throws InvalidThresholdValueException;

    void readThresholdLimitFromProperties();

    void validateThresholdLimitReached() throws HitErrorsThresholdException;

    void calculateThresholdPercent();

    boolean readCSV(String path);

    void printSummary();
}
