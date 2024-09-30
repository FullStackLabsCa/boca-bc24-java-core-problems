package trade_analytic_multithreading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class CliDashboard {
    public static void positionsDBQuery() {
        String topSellBuyTrades  ="SELECT security_id, SUM(CAST(position AS DECIMAL)) AS total_quantity FROM positions WHERE created_at >= NOW() - INTERVAL 1 DAY GROUP BY security_id ORDER BY total_quantity DESC LIMIT 10;";

        try (Connection connection = DatabaseConnector.getConnection();
            PreparedStatement topSellBuyTradesStat = connection.prepareStatement(topSellBuyTrades)){
            ResultSet topSellBuyTradesResultSet = topSellBuyTradesStat.executeQuery();
            while (topSellBuyTradesResultSet.next()){

                System.out.println(topSellBuyTradesResultSet.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
