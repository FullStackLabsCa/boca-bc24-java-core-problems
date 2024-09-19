package trading.RepositoryLayer;
import com.zaxxer.hikari.HikariDataSource;
import trading.Model.TradingValues;
import java.sql.*;
import java.util.List;

public class TradingRep {
    public static void insertdata(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException {

        String query = "Insert into Trades(trade_id,ticker_symbol,quantity,price,trade_date) VALUES(?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            connection.setAutoCommit(false);
            for (TradingValues tradingValues : tradingValuesList) {
                if (selectData(connection, tradingValues.getTickerSymbol())) {
                    preparedStatement.setString(1, tradingValues.getTradeId());
                    preparedStatement.setString(2, tradingValues.getTickerSymbol());
                    preparedStatement.setInt(3, tradingValues.getQuantity());
                    preparedStatement.setDouble(4, tradingValues.getPrice());
                    preparedStatement.setDate(5, Date.valueOf(tradingValues.gettradeDate()));
                    preparedStatement.addBatch();
                } else {
                    System.out.println("ticker Symbol dont exist. Enter : "+tradingValues.getTickerSymbol());
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean selectData(Connection connection, String tickerSymbol) {
        String query = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        boolean validation = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, tickerSymbol);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
