package trading.RepositoryLayer;
import com.zaxxer.hikari.HikariDataSource;
import trading.Model.TradingValues;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TradingRep {
    public static void insertdata(HikariDataSource dataSource, List<TradingValues> tradingValuesList) throws SQLException {
        String query = "Insert into Trades(trade_id,ticker_symbol,quantity,price,trade_date) VALUES(?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
           connection.setAutoCommit(false);
            for (TradingValues tradingValues : tradingValuesList) {
                preparedStatement.setString(1, tradingValues.getTradeId());
                preparedStatement.setString(2, tradingValues.getTickerSymbol());
                preparedStatement.setInt(3, tradingValues.getQuantity());
                preparedStatement.setDouble(4, tradingValues.getPrice());
                preparedStatement.setDate(5, Date.valueOf(tradingValues.gettradeDate()));
                preparedStatement.addBatch();
            }
             preparedStatement.executeBatch();
             connection.commit();
            }
        catch (SQLException e){
            e.printStackTrace();
        }

        }
    }
