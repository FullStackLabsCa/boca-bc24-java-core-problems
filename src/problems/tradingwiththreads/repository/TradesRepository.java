package problems.tradingwiththreads.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.tradingwiththreads.model.RawPayloadPOJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TradesRepository {
    public void insertInRawTable(RawPayloadPOJO rawPayload, Connection connection) {
        String rawTableQuery = "INSERT into trade_payloads_table(trade_id, status, status_reason, payload) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(rawTableQuery)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, rawPayload.getTradeId());
            preparedStatement.setString(2, rawPayload.getStatus());
            preparedStatement.setString(3, rawPayload.getStatusReason());
            preparedStatement.setString(4, rawPayload.getPayload());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
