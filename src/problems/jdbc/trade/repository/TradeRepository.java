package jdbc.trade.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeRepository {

    public static String getTradeId(Connection connection, String tradeId) throws SQLException {
        String selectQuery = "SELECT trade_id FROM Trades WHERE trade_id = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, tradeId);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("trade_id");
        }
        return null;
    }

    public static String getTickerSymbol(Connection connection, String tickerSymbol) throws SQLException {
        String selectQuery = "SELECT symbol FROM SecuritiesReference WHERE symbol = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, tickerSymbol);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("symbol");
        }
        return null;
    }
}
