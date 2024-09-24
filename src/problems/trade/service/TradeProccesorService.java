package problems.trade.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.trade.exceptions.HitErrorsThresholdException;
import problems.trade.exceptions.HitInsertErrorsThresholdException;
import problems.trade.interfaces.TradeDBWriter;
import problems.trade.model.Trade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static problems.trade.TradeRunner.*;
import static problems.trade.Util.CreateErrorTradefile.createErrorFile;
import static problems.trade.dao.TradeDAO.isValidTickerSymbolDB;


public class TradeProccesorService implements TradeDBWriter {

    public static int executedTradesCount = 0;
    public static int errorTradesCount = 0;
    public static List<String> invalidInsertTradeList = new ArrayList<>();
    public static List<Trade> validtradeList;


    @Override
    public int tradeDBWriter(HikariDataSource dataSource, List<Trade> tradeList) throws SQLException, HitErrorsThresholdException, HitInsertErrorsThresholdException {

        validtradeList = new ArrayList<>(tradeList);

        String query = "INSERT INTO Trades (trade_id,trade_identifier, ticker_symbol,quantity,price,trade_date) VALUES (?,?,?,?,?,?)";
        Connection connection = dataSource.getConnection();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            for (Trade trade : validtradeList) {
                if (isValidTickerSymbolDB(trade.getTickerSymbol())) {
                    setPreparedStatement(trade, pstmt);
                } else {
                    invalidInsertTradeList.add(trade + " - Invalid Ticker Symbol");

                    int totalTrades = invalidTradeList.size() + validtradeList.size();
                    int invalidTrades = invalidInsertTradeList.size() + invalidTradeList.size();
                    double failurePercentage = ((double) invalidTrades / totalTrades) * 100;
                    if (failurePercentage > tradeThreshold) {
                        createErrorFile(invalidInsertTradeList, "InsertError");
                        throw new HitInsertErrorsThresholdException("Threshold Reached! Retry with a valid file.");
                    }
                }
            }
            if (!invalidInsertTradeList.isEmpty()) {
                createErrorFile(invalidInsertTradeList, "InsertError");
            }
            int[] resultSet = pstmt.executeBatch();
            handleBatchResults(resultSet);

            if (!checkThresholdForTrades()) {
                connection.commit();
                System.out.println("Batch executed " + executedTradesCount + " transactions successfully."
                        + "\nInvalid Insert Trades : " + invalidInsertTradeList.size()
                        + "\nInvalid Read Trades :" + invalidTradeList.size());
            } else {
                connection.rollback();
                throw new HitErrorsThresholdException(" Hit Error Threshold Exception !! ");
            }
        } catch (BatchUpdateException e) {
            checkBatchUpdateExceptionRows(e);
            connection.rollback();
            throw new HitInsertErrorsThresholdException("Hit Insert Error Threshold Exception !! ");
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return executedTradesCount;
    }

    private static void setPreparedStatement(Trade trade, PreparedStatement pstmt) throws SQLException {
        System.out.println("trade.getTradeId" + trade.getTradeId());
        pstmt.setString(1, trade.getTradeId());
        pstmt.setString(2, trade.getTradeIdentifier());
        pstmt.setString(3, trade.getTickerSymbol());
        pstmt.setInt(4, trade.getQuantity());
        pstmt.setDouble(5, trade.getPrice());
        pstmt.setDate(6, trade.getTrade_date());
        pstmt.addBatch();
    }

    private static void handleBatchResults(int[] resultSet) {
        for (int i = 0; i < resultSet.length; i++) {
            if (resultSet[i] == 1) {
                executedTradesCount++;
            } else if (resultSet[i] == 0) {
                errorTradesCount++;
            }
        }
        System.out.println("executedTradesCount: " + executedTradesCount);
        System.out.println("errorTradesCount: " + errorTradesCount);
    }

    private static boolean checkThresholdForTrades() {
        boolean reachedThreshold = false;
        int totalTradeCount = invalidTradeList.size() + validtradeList.size();
        int errorTradeCount = errorTradesCount + invalidTradeList.size() + invalidInsertTradeList.size();
        if (totalTradeCount == 0) {
            System.out.println("No trades to calculate.");
            return false;
        }
        double errorPercentageExecuted = ((double) errorTradeCount / totalTradeCount) * 100;
        System.out.println("percentageExecuted >>>>" + errorPercentageExecuted);
        if (errorPercentageExecuted > tradeThreshold) {
            System.out.println("Threshold reached. Rolling back Transaction");
            reachedThreshold = true;
        }

//        System.out.println("Trade Threshold :"+tradeThreshold);
//        System.out.println("executedTradesCount :"+executedTradesCount);
//        System.out.println("errorTradesCount :"+errorTradesCount);
//        System.out.println("invalidTradeList :"+invalidTradeList.size());
//        System.out.println("invalidInsertTradeList :"+invalidInsertTradeList.size());


        return reachedThreshold;
    }

    private static int checkBatchUpdateExceptionRows(BatchUpdateException e) {
        int[] updateCounts = e.getUpdateCounts();
        List<String> failedTrades = new ArrayList<>();
        for (int i = 0; i < updateCounts.length; i++) {
            if (updateCounts[i] == Statement.EXECUTE_FAILED) {
                failedTrades.add(validtradeList.get(i).getTradeId()); // Retrieve the failed trade
            }
        }
        System.out.println("Failed Trades : " + failedTrades);
        return failedTrades.size();
    }
}
