package jdbc.trade.tradecontract;

import jdbc.trade.exceptions.HitErrorsThresholdException;
import jdbc.trade.model.TradeData;

import java.io.FileNotFoundException;
import java.util.List;

public interface TradeFileReader {
    List<TradeData> readDataFromCsvFile(String filePath, double errorThreshold) throws HitErrorsThresholdException;
}
