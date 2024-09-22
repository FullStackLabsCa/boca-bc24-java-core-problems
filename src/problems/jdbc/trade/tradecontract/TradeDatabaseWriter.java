package jdbc.trade.tradecontract;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.trade.exceptions.HitInsertErrorsThresholdException;
import jdbc.trade.model.TradeData;

import java.util.List;

public interface TradeDatabaseWriter {
    void writeRecordsToDB(HikariDataSource dataSource, List<TradeData> trades, double errorThreshold) throws HitInsertErrorsThresholdException;
}
