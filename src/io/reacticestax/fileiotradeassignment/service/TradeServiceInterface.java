package io.reacticestax.fileiotradeassignment.service;

import io.reacticestax.fileiotradeassignment.customExceptionClasses.HitReadFileErrorsThresholdException;
import io.reacticestax.fileiotradeassignment.model.TradePOJO;

import java.io.FileNotFoundException;

public interface TradeServiceInterface {

    void setErrorThreshold(double threshold);

    void setFilePath(String filePath);

    void readTradeFile() throws FileNotFoundException, HitReadFileErrorsThresholdException;

    boolean validateTradePOJO(TradePOJO trade);

    void logError(String message, String fileName);
}
