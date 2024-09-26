package problems.tradefileparser.reader;

import problems.tradefileparser.model.TradeModel;

import java.io.IOException;
import java.util.List;

public interface TradeFileReader {
    List<TradeModel> parseTradeFile(String filePath) throws IOException;
}
