package problems.jdbc.trading.service;

public interface ReadAndWriteErrorLog {
    void readFromFileErrorLog(String fileName, String line);

    void writeErrorLog(String fileName, String line);
}
