package problems.jdbc.trading.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;

import java.io.IOException;
import java.util.Map;

public interface ReadFileInterface {

    void readFileAndInitializeDataSource(String path, double thresholdValue, HikariDataSource dataSource);

    Map<Integer, Trade> readCSVFile(String path, ErrorChecking errorChecking) throws IOException, HitErrorsThresholdException;
}
