package problems.jdbc.trading.service;

import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;

import java.io.IOException;
import java.util.Map;

public interface TradeServiceInterface {

    void readFileAndInitializeDataSource(String path, double thresholdValue);

    double fetchThresholdValue();

    Map<Integer, Trade> readCSVFile(String path, ErrorChecking errorChecking) throws IOException, HitErrorsThresholdException;

    Trade createTradeRecord(String line);

    void printSummary(int records, int insertions, int errors);

    void readFromFileErrorLog(String fileName, String line);

    void writeErrorLog(String fileName, String line);

    boolean isThresholdExceeded(ErrorChecking errorChecking);
}
