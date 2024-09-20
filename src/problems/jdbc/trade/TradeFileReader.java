package problems.jdbc.trade;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface TradeFileReader {
    List<TradeTransaction> processFile(String filePath) throws Exception;
}
