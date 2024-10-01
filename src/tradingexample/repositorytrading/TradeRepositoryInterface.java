package tradingexample.repositorytrading;

import com.zaxxer.hikari.HikariDataSource;
import tradingexample.exceptiontrading.HitErrorsThresholdException;
import tradingexample.modeltrading.ErrorChecker;
import tradingexample.modeltrading.TradeTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface TradeRepositoryInterface {

    void insertTrade(Map<Integer, TradeTransaction> tradeMap, HikariDataSource dataSource, ErrorChecker errorCheck) throws HitErrorsThresholdException, SQLException;

    boolean checkSecurities(Connection connection, String symbol);

}
