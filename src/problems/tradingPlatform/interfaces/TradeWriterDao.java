package problems.tradingPlatform.interfaces;

import problems.tradingPlatform.models.Trade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface TradeWriterDao {

    void writeTradeData(List<Trade> tradeList, double errorThresholdCount);
    void writeDataInDatabase(List<Trade> tradeList, Connection conn, PreparedStatement preparedStatement, boolean autoCommit) throws SQLException;
}
