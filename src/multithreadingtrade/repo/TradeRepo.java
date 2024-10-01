package multithreadingtrade.repo;

import multithreadingtrade.trademodel.RawPayLoad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TradeRepo {
    public static void insertIntoRawPayLoad(RawPayLoad rawPayLoad, Connection connection){
        String query = "Insert into trade_payloads (trade_id, status, status_reason, payload) values(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rawPayLoad.getTradeID());
            preparedStatement.setString(2, rawPayLoad.getStatus());
            preparedStatement.setString(3, rawPayLoad.getStatusReason());
            preparedStatement.setString(4, rawPayLoad.getPayLoads());
            preparedStatement.execute();
            System.out.println("File inserted sucessfully for :" + rawPayLoad.getTradeID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
