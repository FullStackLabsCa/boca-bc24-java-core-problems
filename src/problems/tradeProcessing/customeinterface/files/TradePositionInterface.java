package problems.tradeProcessing.customeinterface.files;

import java.sql.SQLException;

public interface TradePositionInterface {
    void upsertPosition(String accountId, String securityId, int quantity, String direction) throws SQLException;
}
