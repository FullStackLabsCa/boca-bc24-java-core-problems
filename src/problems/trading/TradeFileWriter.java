package problems.trading;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeFileWriter {
    public static void insertQuery(LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) throws Exception {
        String logFilePath = "/Users/Gaurav.Manchanda/Sources/Student-mode/error_log.txt";
        Connection connection = DatabaseConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

            String insertQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
            String lookupQuery = "SELECT symbol FROM SecuritiesReference WHERE symbol = ?";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            PreparedStatement lookupStatement = connection.prepareStatement(lookupQuery);

            for (TradeTransaction tradeTransaction : tradingTransactionDeQueue) {
                String symbol = "";
                ResultSet resultSet = getResultSetForLookupQuery(tradeTransaction, lookupStatement);
                if (resultSet.next()) {
                    symbol = resultSet.getString("symbol");
                }

                if (symbol.equals(tradeTransaction.getTickerSymbol()) && tradeTransaction.getTickerSymbol() != "") {
                    preparingStatementForBatch(tradeTransaction, statement);
                    statement.addBatch();
                } else {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
                        writer.write("Error while inserting trade to DB >>>"+String.valueOf(tradeTransaction));
                        writer.newLine();
                    } catch (IOException e) {
                        e.getMessage();
//                        errorLengthInQueue++;
                    }
                }
            }

            statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
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
