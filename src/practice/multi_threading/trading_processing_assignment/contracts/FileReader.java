package practice.multi_threading.trading_processing_assignment.contracts;

import problems.trading_3way_with_object.Trade;

import java.util.List;

public interface FileReader {
    List<Trade> readTrades(String inputFilePath) throws Exception;
}
