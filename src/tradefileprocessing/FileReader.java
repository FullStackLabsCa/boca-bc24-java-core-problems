package tradefileprocessing;

import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<String[]> readFile(String filePath) throws IOException;
    List<String[]> filterData(List<String[]> trades);
}
