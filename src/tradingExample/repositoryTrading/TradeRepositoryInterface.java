package tradingExample.repositoryTrading;

import com.zaxxer.hikari.HikariDataSource;
import tradingExample.exceptionTrading.HitErrorsThresholdException;
import tradingExample.modelTrading.ErrorChecker;
import tradingExample.modelTrading.TradeTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface TradeRepositoryInterface {

    void insertTrade(Map<Integer, TradeTransaction> tradeMap, HikariDataSource dataSource, ErrorChecker errorCheck) throws HitErrorsThresholdException, SQLException;

    boolean checkSecurities(Connection connection, String symbol);

}
