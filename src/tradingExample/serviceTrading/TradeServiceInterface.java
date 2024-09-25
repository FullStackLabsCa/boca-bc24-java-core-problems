package tradingExample.serviceTrading;
import tradingExample.exceptionTrading.InvalidThresholdValueException;
import tradingExample.modelTrading.ErrorChecker;
import tradingExample.modelTrading.TradeTransaction;

import java.io.IOException;
import java.util.Map;

public interface TradeServiceInterface {

    void readFileAndInitializeDataSource(String filepath, double errorThreshold) throws InvalidThresholdValueException,tradingExample.exceptionTrading.HitErrorsThresholdException;

    double fetchThresholdValue() throws InvalidThresholdValueException;

    Map<Integer, TradeTransaction> readCSVFile(String path, ErrorChecker errorChecker) throws IOException, tradingExample.exceptionTrading.HitErrorsThresholdException;

    void printSummary(int records, int insertions, int errors);

    //void readFromFileErrorLog(String fileName, String line);

    void writerErrorLog(String fileName, String line);

    boolean isThresholdExceeded(ErrorChecker errorChecker);
}