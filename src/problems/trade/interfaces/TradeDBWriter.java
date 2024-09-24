package problems.trade.interfaces;

import com.zaxxer.hikari.HikariDataSource;
import problems.trade.exceptions.HitErrorsThresholdException;
import problems.trade.exceptions.HitInsertErrorsThresholdException;
import problems.trade.model.Trade;

import java.sql.SQLException;
import java.util.List;

public interface TradeDBWriter {
      int tradeDBWriter(HikariDataSource dataSource, List<Trade> tradeList) throws SQLException, HitErrorsThresholdException, HitInsertErrorsThresholdException ;
}
