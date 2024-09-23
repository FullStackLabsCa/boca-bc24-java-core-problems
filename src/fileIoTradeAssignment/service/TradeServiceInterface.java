package fileIoTradeAssignment.service;

import fileIoTradeAssignment.customExceptionClasses.HitReadFileErrorsThresholdException;
import fileIoTradeAssignment.model.TradePOJO;

import java.io.FileNotFoundException;

public interface TradeServiceInterface {

    void setErrorThreshold(double threshold);

    void setFilePath(String filePath);

    void readTradeFile() throws FileNotFoundException, HitReadFileErrorsThresholdException;

    boolean validateTradePOJO(TradePOJO trade);

    void logError(String message, String fileName);
}
