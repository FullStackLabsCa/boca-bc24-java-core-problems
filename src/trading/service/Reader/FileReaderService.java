package trading.service.Reader;

import trading.exceptions.ReadFileThresholdException;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public interface FileReaderService {
    List<String> readFile(String filePath) throws FileNotFoundException;
    void filterFile(List<String> lines) throws ReadFileThresholdException, ParseException;
}
