package problems.jdbc.trade;

import problems.jdbc.hikari.DataSource;

import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import static problems.jdbc.trade.TradeFileReaderImpl.counter;

public class TradeFileWriterImpl implements TradeFileWriter {

    static Connection connection;
    public Error error;

   public static int errorCounter;

    static {
        try {
            connection = DataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TradeFileWriterImpl() throws Exception {
    }

    public TradeFileWriterImpl(Error error) throws Exception {
    this.error = error;
    }

    // Insert a new account for a credit card number
    @Override
    public int writeTradeToDatabase(List<TradeTransaction> tradeTransactions) throws SQLException {
        connection.setAutoCommit(false);
        int[] numberOfRowsInserted;
        TradeFileWriterImpl.errorCounter = error.getErrorCounter();
        String insertQuery = "INSERT INTO Trades (trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";
        String lookupQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        PreparedStatement lookupStatement = connection.prepareStatement(lookupQuery);
        try {
            for (TradeTransaction tradeTransaction : tradeTransactions) {
                lookupStatement.setString(1, tradeTransaction.getTickerSymbol());
                ResultSet resultSet = lookupStatement.executeQuery();
                if(!resultSet.next()) {
                    writeTologFile(error.getErrorLog(), counter, tradeTransaction.toString());
                    continue;
                }
                System.out.println("Inserting id" + tradeTransaction.getTradeIdentifier());
                stmt.setString(1, tradeTransaction.getTradeIdentifier());
                stmt.setString(2, tradeTransaction.getTickerSymbol());
                stmt.setInt(3, tradeTransaction.getQuantity());
                stmt.setDouble(4, tradeTransaction.getPrice());
                stmt.setDate(5, new Date(tradeTransaction.getTradeDate().getTime()));
                stmt.addBatch();
            }
            if(errorCounter > (error.getRecordSize() * error.getErrorThreshold()) / 100 ){
                throw new HitErrorsThresholdException("Threshold Error exception...");
            }
            numberOfRowsInserted = stmt.executeBatch();
            System.out.println("Number of rows inserted " + numberOfRowsInserted.length);
        } catch (HitErrorsThresholdException e) {
            connection.rollback();
            //handle here checked exception after comparing the offset file;
            throw e;
        }
        System.out.println("Number of rows failed: " + errorCounter );
        System.out.println("Batch Insertion Successfully");
        connection.commit();
        connection.setAutoCommit(true);
        return numberOfRowsInserted.length;
    }


    private static void writeTologFile(PrintWriter errorLog,int lineNumber, String line) {
            errorLog.println("No Securities Matched for the record of: " + line);
            errorCounter++;
        }

}
