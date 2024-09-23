package problems.trading.repository;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.tradingmodel.TradingValues;
import problems.trading.repository.TradingRepository;

import java.sql.*;
import java.util.List;

public class TradingRepository {


    public static void prepareStatements(HikariDataSource dataSource, List<TradingValues> listOfTradingValues) {
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";

        //getting the connection
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);


            //System.out.println("TradingList size = " + listOfTradingValues.size());

            for (TradingValues tradingList : listOfTradingValues) {
                //System.out.println("tradingList.getTradeId()=" + tradingList.getTradeId());
                //preparedStatement.setString(1, tradingList.getTradeId());
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
        System.out.println("TradingList size = " + listOfTradingValues.size());

    }

    //Verification if the ticker symbol exists in the database or not

    public static boolean isTickerSymbolValid(Connection connection, String tickerSymbol) {
        String lookupQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        try (PreparedStatement stmt = connection.prepareStatement(lookupQuery)) {
            stmt.setString(1, tickerSymbol);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return false;
        }

    }
    public boolean isValidTickerSymbol(Connection connection, String line){
        String[] data = line.split(",");
        String tickerSymbol = data[2].trim();

        return false; //check
    }
}

