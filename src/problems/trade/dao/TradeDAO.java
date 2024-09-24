package problems.trade.dao;

import problems.trade.config.HikariCPConfig;

import javax.sql.DataSource;
import java.sql.*;

public class TradeDAO {

    static DataSource dataSource = HikariCPConfig.getDataSource();

    public static boolean isValidTickerSymbolDB(String symbol) {
        String selectQuery = "SELECT (symbol) FROM SecuritiesReference WHERE symbol= ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectQuery)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}

// Insert only if there's valid security in DB Table.
//        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) SELECT ?, ?, ?, ?, ?, ? " +
//                "FROM SecuritiesReference WHERE symbol = ?";