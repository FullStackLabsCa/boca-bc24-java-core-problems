package problems.tradeProcessing.customeinterface.files;

import java.sql.SQLException;

public interface TradePayloadServiceInterface {
    String fetchTradePayload(String tradeId) throws SQLException;
}
