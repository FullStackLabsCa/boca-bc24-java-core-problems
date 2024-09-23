package tradingExample.repositoryTrading;

import com.zaxxer.hikari.HikariDataSource;
import tradingExample.exceptionTrading.HitErrorsThresholdException;
import tradingExample.modelTrading.ErrorChecker;
import tradingExample.modelTrading.TradeTransaction;
import tradingExample.serviceTrading.TradeService;


import java.sql.*;
import java.util.ArrayList;
import java.util.Map;


public class TradeRepository implements TradeRepositoryInterface {

    @Override
    public void insertTrade(Map<Integer,TradeTransaction>tradeMap, HikariDataSource dataSource, ErrorChecker errorCheck) throws HitErrorsThresholdException, SQLException {
          String query = "INSERT INTO Trades (trade_id, trade_identifier,ticker_symbol, quantity, price,trade_date) VALUES (?, ?,?, ?, ?, ?)";
          Connection connection = null;

          try {
              connection = dataSource.getConnection();
              connection.setAutoCommit(false);
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              int batchSize = 50;
              int batchNumber = 0;
              ArrayList<Integer> recordNumber = new ArrayList<>(tradeMap.keySet());
              for (Integer record : recordNumber) {
                  TradeService tradeService=new TradeService();
                  if (checkSecurities(connection, tradeMap.get(record).getTickerSymbol())) {
                      preparedStatement.setString(1, tradeMap.get(record).getTradeId());
                      preparedStatement.setString(2, tradeMap.get(record).getTradeIdentifier());
                      preparedStatement.setString(3, tradeMap.get(record).getTickerSymbol());
                      preparedStatement.setInt(4, tradeMap.get(record).getQuantity());
                      preparedStatement.setDouble(5, tradeMap.get(record).getPrice());
                      preparedStatement.setDate(6, Date.valueOf(tradeMap.get(record).getTradeDate()));
                      preparedStatement.addBatch();
                      batchNumber++;
                      if (recordNumber.size() - 1 == recordNumber.indexOf(record) || batchNumber == batchSize) {
                          errorCheck.incrementInsertCount(batchNumber);
                          batchNumber = 0;
                          preparedStatement.executeBatch();
                      }
                  } else {
                      errorCheck.incrementErrorCount();
                      tradeService.writerErrorLog("/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src" +
                              "/tradingExample/utilityTrading/writerErrorLog.txt", new java.util.Date() + " Insertion error on line "
                              + record + "-->ERROR: Invalid " + "ticker_symbol.");
                      if (tradeService.isThresholdExceeded(errorCheck)) {
                          errorCheck.setInsertion(0);
                          connection.rollback();
                          throw new HitErrorsThresholdException("Insertion failed...Threshold limit reached.");
                      } else if (recordNumber.size() - 1 == recordNumber.indexOf(record)) {
                          errorCheck.incrementInsertCount(batchNumber);
                          batchNumber = 0;
                          preparedStatement.executeBatch();
                      }
                  }
              }
              connection.commit();
          }catch (SQLException e){
              if(connection!=null){
                  errorCheck.setInsertion(0);
                  connection.rollback();
              }
              System.out.println(e.getMessage());
          } finally {
              if(connection!=null) connection.setAutoCommit(true);
          }
          }
          @Override
    public boolean checkSecurities(Connection connection, String symbol) {
        boolean value=false;
        try{
        String lookupQuery = " SELECT 1 FROM SecuritiesReference WHERE symbol = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(lookupQuery);
            preparedStatement.setString(1, symbol);
            ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                   value=true;
                }
            }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return value;
    }

}
