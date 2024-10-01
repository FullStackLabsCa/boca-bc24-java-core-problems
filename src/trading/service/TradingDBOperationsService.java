package trading.service;

import trading.dbconnection.DatabseConnectionPool;
import trading.model.TradingModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TradingDBOperationsService {

    public List symbolsToList() throws Exception {
        List<String> symbolList = new ArrayList<>();
        String sql = "SELECT symbol FROM SecuritiesReference";
        try(Connection connection = DatabseConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                symbolList.add(rs.getString("symbol"));
            }
            System.out.println(symbolList);
        }
        return symbolList;
    }

    public void insertTradingModel(List<TradingModel> models) throws Exception{
        String sql = "INSERT INTO Trades(trade_id,trade_identifier,ticker_symbol,quantity,price,trade_date) VALUES(?,?,?,?,?,?)";
        try(Connection connection = DatabseConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for(TradingModel model : models) {
                    preparedStatement.setString(1, model.getTradeId());
                    preparedStatement.setString(2, model.getTradeIdentifier());
                    preparedStatement.setString(3, model.getTickerSymbol());
                    preparedStatement.setInt(4, model.getQuantity());
                    preparedStatement.setDouble(5, model.getPrice());
                    preparedStatement.setString(6, model.getDate());
                    preparedStatement.addBatch();
                }
                int[] rowsInserted = preparedStatement.executeBatch();

                connection.commit();
                System.out.println(rowsInserted.length+"trading models inserted succesfully!");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction rolled back. SQL Exception: " + e.getMessage());
                e.printStackTrace();
            }
        }catch(SQLException e){
            System.out.println("SQL Exception: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
