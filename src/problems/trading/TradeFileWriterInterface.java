package problems.trading;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TradeFileWriterInterface {
    void fileWriter(ArrayList<TradeTransaction> tradingTransactionArrayList, Connection connection) throws SQLException;
}
