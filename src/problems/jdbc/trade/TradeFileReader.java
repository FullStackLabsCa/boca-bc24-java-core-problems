package problems.jdbc.trade;

import java.util.List;

public interface TradeFileReader {
    List<TradeTransaction> processFile(String filePath) throws Exception;
}
