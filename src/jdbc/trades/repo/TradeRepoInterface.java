package jdbc.trades.repo;

import jdbc.trades.model.TradePOJO;
import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

public interface TradeRepoInterface {
    int processBatch(List<TradePOJO> datas, HikariDataSource dataSource);
}