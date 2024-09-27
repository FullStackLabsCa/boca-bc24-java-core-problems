package problems.jdbc.trading.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.Trade;

import java.io.IOException;
import java.util.Map;

public interface ReadFile {

    void readFileAndInitializeDataSource(String path, double thresholdValue, HikariDataSource dataSource);

    Map<Integer, Trade> readCSVFile(String path) throws IOException, HitErrorsThresholdException;
}
