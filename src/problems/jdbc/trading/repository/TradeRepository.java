package problems.jdbc.trading.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.Trade;

import java.sql.SQLException;
import java.util.Map;

public interface TradeRepository {
    void insertTrade(Map<Integer, Trade> trades, HikariDataSource dataSource) throws HitErrorsThresholdException, SQLException;
}
