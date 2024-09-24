package problems.trading_3way_with_object.utility;

import problems.trading_3way_with_object.Trade;
import problems.trading_3way_with_object.contract.TradeWriter;
import problems.trading_3way_with_object.exception.HitInsertErrorsThresholdException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TradeDatabaseWriter implements TradeWriter {

    private static final String insertQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String lookupQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
    public static List<String> errorWriterMessageList = new ArrayList<>();
    CsvTradeReader csvTradeReader = new CsvTradeReader();
    private static final int batchSize = 50;

    @Override
    public int writeTrades(List<Trade> trades, double errorThreshold) throws Exception {
        int totalTrades = trades.size();
        int batchCounter = 0;
        int errorBussinesLogicCounter = 0;
        int successfulInserts = 0;
        int lineCounter = (CsvTradeReader.lineCount) - 1;
        int errorReaderCounter = (CsvTradeReader.errorReaderCounter);
        int successfullyParsedCounter = (CsvTradeReader.successfullyParsedCounter);
        List<String> errorReaderMessageList = csvTradeReader.errorReaderMessageList;

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement psInsert = conn.prepareStatement(insertQuery);
             PreparedStatement psLookup = conn.prepareStatement(lookupQuery);
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter(
                     "/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/problems/trading_3way_with_object/files/db_error_log.txt"))) {

            conn.setAutoCommit(false);

            for (Trade trade : trades) {
                try {
                    // Validate ticker symbol
                    psLookup.setString(1, trade.getTickerSymbol());
                    ResultSet rs = psLookup.executeQuery();
                    if (!rs.next()) {
                        throw new SQLException("Invalid ticker symbol: " + trade.getTickerSymbol());
                    }
                    // Prepare batch for insertion
                    psInsert.setString(1, trade.getTradeId());
                    psInsert.setString(2, trade.getTradeIdentifier());
                    psInsert.setString(3, trade.getTickerSymbol());
                    psInsert.setInt(4, trade.getQuantity());
                    psInsert.setDouble(5, trade.getPrice());
                    psInsert.setString(6, trade.getTradeDate());
                    psInsert.addBatch();
                    batchCounter++;

                } catch (SQLException e) {
                    String errorWriterMessage = "Error for trade: " + trade.getTradeId() + " - " + e.getMessage();
                    errorWriter.write(errorWriterMessage);
                    errorWriter.newLine();
                    errorWriterMessageList.add(errorWriterMessage);
                    errorBussinesLogicCounter++;
                }
                if (batchCounter == batchSize) {
                    successfulInserts += ExecuteBatch.executeBatch(psInsert, conn, errorWriter, errorWriterMessageList);
                    batchCounter = 0;
                }
            }
            // Execute any remaining batch
            if (batchCounter > 0) {
                successfulInserts += ExecuteBatch.executeBatch(psInsert, conn, errorWriter, errorWriterMessageList);
            }

//            int[] batchResults = psInsert.executeBatch();
//            for (int result : batchResults) {
//                if (result == Statement.SUCCESS_NO_INFO || result > 0) {
//                    successfulInserts++;
//                }
//            }

            // Error threshold exceeded
            if ((double) (errorBussinesLogicCounter + errorReaderCounter) / lineCounter * 100 > errorThreshold) {
                conn.rollback();
                throw new HitInsertErrorsThresholdException("Error threshold exceeded during batch processing!");
            } else {
                conn.commit();
            }
        }

        PrintSummary.printSummary(lineCounter, successfullyParsedCounter, successfulInserts,
                errorReaderCounter, errorBussinesLogicCounter, errorReaderMessageList, errorWriterMessageList);
        return successfulInserts;

    }

}
