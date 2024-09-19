package trading_parser.repo;

import trading_parser.model.Trade;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static trading_parser.service.TradeParserEngine.successfullInsertsCount;
import static trading_parser.service.TradeParserEngine.udpateInsertionLogs;

public class TradeParserRepo {
    public static int batchSize = 5;
    //Repo
    public static void insertTrades(Connection connection, Logger logger, List<Trade> tradesList) throws SQLException {
        int originalSizeOfTable = getTradeTableSize(connection);
        int batchNumber=0;
        try{
            String insertionQuery = """
                    insert into Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date)
                    values (?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement psInsertQuery = connection.prepareStatement(insertionQuery);
            int counter = 0, tradesBatched = 0;
            for (Trade trade : tradesList) {
                psInsertQuery.setString(1, trade.getTrade_id());
                psInsertQuery.setString(2, trade.getTrade_identifier());
                psInsertQuery.setString(3, trade.getTicker_symbol());
                psInsertQuery.setInt(4, trade.getQuantity());
                psInsertQuery.setDouble(5, trade.getPrice());
                psInsertQuery.setDate(6, Date.valueOf(trade.getTrade_date()));
                psInsertQuery.addBatch();
                counter++;
                tradesBatched++;
                if (counter > batchSize || tradesBatched == tradesList.size()) {
                    psInsertQuery.executeBatch();
                    successfullInsertsCount += counter;
                    batchNumber++;
                    counter = 0;
                }
            }
        } catch (BatchUpdateException e){
            int[] failureLog = e.getUpdateCounts();
            for(int i = 0 ; i < failureLog.length; i++){
                if(failureLog[i] < 0){
                    logger.log(Level.WARNING, "Insertion Error: Failed to insert -> " + tradesList.get(batchNumber*batchSize + i));
                }
            }
            udpateInsertionLogs(connection, tradesList, originalSizeOfTable);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            udpateInsertionLogs(connection, tradesList, originalSizeOfTable);
        }
    }

    //Repo
    public static int getTradeTableSize(Connection connection){
        try{
            String query = "select count(*) as total_trades from Trades;";
            PreparedStatement psQuery = connection.prepareStatement(query);

            ResultSet rsQuery = psQuery.executeQuery();
            if(rsQuery.next()) {
                return rsQuery.getInt("total_trades");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return 0;
    }

    public static boolean tradesBusinessValidation(Connection conn, Trade trade) {
        try {
            String lookupQuery = "select 1 from SecuritiesReference where symbol = ?";
            PreparedStatement psLookUp = conn.prepareStatement(lookupQuery);

            psLookUp.setString(1, trade.getTicker_symbol());

            ResultSet rsLookUp = psLookUp.executeQuery();
            if (rsLookUp.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
