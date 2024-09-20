package problems.tradingPlatform.interfaces;

import problems.tradingPlatform.models.Trade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface TradeWriterDao {

    int writeTradeData(List<Trade> tradeList,Connection conn,boolean forTest);
    int writeDataInDatabase(List<Trade> tradeList, Connection conn, PreparedStatement preparedStatement, boolean autoCommit) throws SQLException;
}
