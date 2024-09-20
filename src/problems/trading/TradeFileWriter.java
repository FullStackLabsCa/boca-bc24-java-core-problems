package problems.trading;

import problems.trading.database.DatabaseConnectionPool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TradeFileWriter {
    private static int counter = 0;

    public static void insertQuery(ArrayList<TradeTransaction> tradingTransactionArrayList) throws Exception {
        Connection connection = DatabaseConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

            String insertQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
            String lookupQuery = "SELECT symbol FROM SecuritiesReference WHERE symbol = ?";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            PreparedStatement lookupStatement = connection.prepareStatement(lookupQuery);

            for (TradeTransaction tradeTransaction : tradingTransactionArrayList) {
                counter++;
                String symbol = "";
                //checking the security symbol
                ResultSet resultSet = getResultSetForLookupQuery(tradeTransaction, lookupStatement);
                if (resultSet.next()) {
                    symbol = resultSet.getString("symbol");
                }

                if (symbol.equals(tradeTransaction.getTickerSymbol()) && tradeTransaction.getTickerSymbol() != "") {
                    //preparing batch
                    preparingStatementForBatch(tradeTransaction, statement);
                    statement.addBatch();
                } else {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(TradeService.writeFile, true))) {
                        int errorAtLine = counter;
                        TradeService.errorCount++;
                        writer.write("Error in the row while inserting trade to DB >>> line number " + (errorAtLine + 1) + " >> " + String.valueOf(tradeTransaction));
                        writer.newLine();
                    } catch (IOException e) {
                        e.getMessage();
                        TradeService.errorCount++;
                    }
                }
                //if threshold limit increases then throwing an exception
                TradeService.checkingThreshold(tradingTransactionArrayList);
            }

            statement.executeBatch();
            connection.commit();

            //printing summary
            printSummary(tradingTransactionArrayList);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    private static void printSummary(ArrayList<TradeTransaction> tradingTransactionArrayList) {
        System.out.println("Total No of rows enter in the file: " + tradingTransactionArrayList.size());
        System.out.println("No of rows enter in the DB: " + (tradingTransactionArrayList.size() - TradeService.errorCount));
        System.err.println("No of rows failed to enter in the DB: " + TradeService.errorCount);
        System.out.println("Data added to database successfully");
    }

    private static ResultSet getResultSetForLookupQuery(TradeTransaction tradeTransaction, PreparedStatement lookupStatement) throws SQLException {
        lookupStatement.setString(1, tradeTransaction.getTickerSymbol());
        ResultSet resultSet = lookupStatement.executeQuery();
        return resultSet;
    }

    private static void preparingStatementForBatch(TradeTransaction tradeTransaction, PreparedStatement statement) throws SQLException {
        statement.setString(1, tradeTransaction.getTradeId());
        statement.setString(2, tradeTransaction.getTradeIdentifier());
        statement.setString(3, tradeTransaction.getTickerSymbol());
        statement.setInt(4, tradeTransaction.getQuantity());
        statement.setDouble(5, tradeTransaction.getPrice());
        statement.setDate(6, tradeTransaction.getTradeDate());
    }
}
