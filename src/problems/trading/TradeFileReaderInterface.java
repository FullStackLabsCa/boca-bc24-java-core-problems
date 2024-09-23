package problems.trading;

import java.io.FileNotFoundException;

public interface TradeFileReaderInterface {
    String fileReader(String fileName) throws FileNotFoundException;
}
