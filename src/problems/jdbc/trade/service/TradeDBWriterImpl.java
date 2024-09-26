package jdbc.trade.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.trade.exceptions.HitInsertErrorsThresholdException;
import jdbc.trade.model.TradeData;
import jdbc.trade.repository.TradeRepository;
import jdbc.trade.tradecontract.TradeDatabaseWriter;
import jdbc.trade.utils.WriteErrorLogs;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TradeDBWriterImpl implements TradeDatabaseWriter {

    public static void printSummary(int listSize, int insertErrorCount, int insertSuccessCount, int duplicateRecords) {
        System.out.println("\n=================Reports of the trade system - writing data in DB==================");
        System.out.println("Total record in file = " + listSize);
        System.out.println("Total records processed = " + listSize + ", Number of errors = " +  insertErrorCount +
                ", Number of successful insert = " + insertSuccessCount + ", Number of duplicates = "+ duplicateRecords);
    }

    public PreparedStatement prepareInsertStatement(PreparedStatement preparedStatement, TradeData tradeData) throws SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate =  format.parse(tradeData.getTradeDate());
            Date date = new Date(utilDate.getTime());
            preparedStatement.setDate(6, date);
        } catch (ParseException e) {
            System.out.println("ParseException>>>" + e.getMessage());
        }

        preparedStatement.setString(1, tradeData.getTradeId());
        preparedStatement.setString(2, tradeData.getTradeIdentifier());
        preparedStatement.setString(3, tradeData.getTickerSymbol());
        preparedStatement.setInt(4, tradeData.getQuantity());
        preparedStatement.setDouble(5, tradeData.getPrice());

        return preparedStatement;
    }

    @Override
    public void writeRecordsToDB(HikariDataSource dataSource, List<TradeData> tradeDataList, double errorThreshold) throws HitInsertErrorsThresholdException {
        int insertErrorCount = 0;
        int insertSuccessCount = 0;
        int duplicateRecords = 0;
        int listSize = tradeDataList.size();
        int maxExpectedErrors = (int) Math.ceil(listSize * (errorThreshold / 100));

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES(?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                for (TradeData tradeData : tradeDataList) {
                    String tradeId = TradeRepository.getTradeId(connection, tradeData.getTradeId());
                    if (tradeId != null) {
                        duplicateRecords++;
                        continue;
                    }

                    if (!processTradeData(connection, tradeData, preparedStatement)) {
                        insertErrorCount++;
                    } else {
                        insertSuccessCount++;
                    }
                }
                preparedStatement.executeBatch();
                validateInsertCounts(connection, maxExpectedErrors, insertErrorCount);
                connection.commit();
                printSummary(listSize, insertErrorCount, insertSuccessCount, duplicateRecords);
            } catch (BatchUpdateException e) {
                handleBatchUpdateException(e, connection, maxExpectedErrors);
            } catch (SQLException e) {
                System.out.println("SQLException in DB Prepare statement>>> " + e.getMessage());
                connection.rollback();
            } catch (HitInsertErrorsThresholdException e) {
                System.err.println("HitInsertErrorsThresholdException>>> " + insertErrorCount);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DB connection>>> " + e.getMessage());
        }
    }

    private boolean processTradeData(Connection connection, TradeData tradeData, PreparedStatement preparedStatement) throws SQLException {
        String tickerSymbol = TradeRepository.getTickerSymbol(connection, tradeData.getTickerSymbol());
        if (tickerSymbol == null) {
            logError(tradeData);
            return false;
        }

        preparedStatement = prepareInsertStatement(preparedStatement, tradeData);
        preparedStatement.addBatch();
        return true;
    }

    private void logError(TradeData tradeData) {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/logs/write_error_log.txt";
        String errorLog = String.join(",", tradeData.getTradeId(), tradeData.getTradeIdentifier(),
                tradeData.getTickerSymbol(), String.valueOf(tradeData.getQuantity()),
                String.valueOf(tradeData.getPrice()), tradeData.getTradeDate());
        WriteErrorLogs.writeErrorLogsToFile("[" + errorLog + "] - Invalid security symbol: [" + tradeData.getTickerSymbol() + "].", filePath, tradeData.getLineNo());
    }

    private void validateInsertCounts(Connection connection, int maxExpectedErrors, int insertErrorCount) throws HitInsertErrorsThresholdException, SQLException {
        if (insertErrorCount >= maxExpectedErrors) {
            System.out.println("maxExpectedErrors=" + maxExpectedErrors);
            System.out.println("insertErrorCount=" + insertErrorCount);
            connection.rollback();
            throw new HitInsertErrorsThresholdException("Hits maximum error threshold while writing to db..." + insertErrorCount);
        }
    }

    private void handleBatchUpdateException(BatchUpdateException e, Connection connection, int maxExpectedErrors) throws SQLException, HitInsertErrorsThresholdException {
        System.out.println("BatchUpdateException>>>" + e.getMessage());

        int[] updateCounts = e.getUpdateCounts();
        int insertSuccessCount = 0;
        int insertErrorCount = 0;

        for (int count : updateCounts) {
            if (count >= 0) {
                insertSuccessCount++;
            } else if (count == Statement.EXECUTE_FAILED) {
                insertErrorCount++;
            }
        }

        if (maxExpectedErrors <= insertErrorCount) {
            connection.rollback();
            throw new HitInsertErrorsThresholdException("Hits maximum error threshold while writing to db..." + maxExpectedErrors);
        }
        connection.commit();
        printSummary(updateCounts.length, insertErrorCount, insertSuccessCount, 0); // Assuming 0 for duplicateRecords here
    }
}
