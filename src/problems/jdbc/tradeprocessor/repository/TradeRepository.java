package problems.jdbc.tradeprocessor.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class TradeRepository {

    public void insertTradeRawPayload(String tradeId, String status) {
        String query = "Insert into trade_payloads trade_id, status, payload values(?, ?, ?)";

    }
}
