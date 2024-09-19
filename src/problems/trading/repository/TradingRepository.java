package problems.trading.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.tradingmodel.TradingValues;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TradingRepository {


    public static void prepareStatements(HikariDataSource dataSource, List<TradingValues> listOfTradingValues) {
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";

        //getting the connection
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            //parameters
            System.out.println("tradingList size=" + listOfTradingValues.size());

                for (TradingValues tradingList : listOfTradingValues) {
                    //System.out.println("tradingList.getTradeId()=" + tradingList.getTradeId());
                    preparedStatement.setString(1, tradingList.getTradeId());
                    preparedStatement.setString(2, tradingList.getTradeIdentifier());
                    preparedStatement.setString(3, tradingList.getTickerSymbol());
                    preparedStatement.setInt(4, tradingList.getQuantity());
                    preparedStatement.setDouble(5, tradingList.getPrice());
                    preparedStatement.setDate(6, Date.valueOf(tradingList.getTradeDate()));
                    //adding to the batch
                    preparedStatement.addBatch();

                }
                preparedStatement.executeBatch();
                connection.commit();


        } catch (SQLException e) {
            System.err.println("Error in preparing statements: " + e.getMessage());
        }

    }
}

