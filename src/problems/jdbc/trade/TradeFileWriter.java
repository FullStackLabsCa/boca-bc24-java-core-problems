package problems.jdbc.trade;

import java.sql.SQLException;
import java.util.List;

public interface TradeFileWriter {
   int writeTradeToDatabase(List<TradeTransaction> tradeTransactions) throws SQLException;
}
