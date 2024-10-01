package tradingexample.servicetrading;
import tradingexample.exceptiontrading.InvalidThresholdValueException;
import tradingexample.modeltrading.ErrorChecker;
import tradingexample.modeltrading.TradeTransaction;

import java.io.IOException;
import java.util.Map;

public interface TradeServiceInterface {

    void readFileAndInitializeDataSource(String filepath, double errorThreshold) throws InvalidThresholdValueException, tradingexample.exceptiontrading.HitErrorsThresholdException;

    double fetchThresholdValue() throws InvalidThresholdValueException;

    Map<Integer, TradeTransaction> readCSVFile(String path, ErrorChecker errorChecker) throws IOException, tradingexample.exceptiontrading.HitErrorsThresholdException;

    void printSummary(int records, int insertions, int errors);


    void writerErrorLog(String fileName, String line);

    boolean isThresholdExceeded(ErrorChecker errorChecker);
}