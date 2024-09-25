package jdbc.trade.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.trade.exceptions.HitErrorsThresholdException;
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
    public static int insertErrorCount = 0, maxExpectedErrors = 0, insertSuccessCount = 0, duplicateRecords = 0;

    public static void printSummary(int listSize) {
        System.out.println("=================Reports of the trade system - writing data in DB==================");
        System.out.println("Total record in file = " + listSize);
        System.out.println("Total records processed = " + listSize + ", Number of errors = " +  insertErrorCount +
                ", Number of successful insert = " + insertSuccessCount + ", Number of duplicates = "+ duplicateRecords);
    }

    public PreparedStatement prepareInsertStatement(PreparedStatement preparedStatement, TradeData tradeData) throws ParseException, SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate =  format.parse(tradeData.getTrade_date());
        Date date = new Date(utilDate.getTime());

        preparedStatement.setString(1, tradeData.getTrade_id());
        preparedStatement.setString(2, tradeData.getTrade_identifier());
        preparedStatement.setString(3, tradeData.getTicker_symbol());
        preparedStatement.setInt(4, tradeData.getQuantity());
        preparedStatement.setDouble(5, tradeData.getPrice());
        preparedStatement.setDate(6, date);
        return preparedStatement;
    }

    @Override
    public void writeRecordsToDB(HikariDataSource dataSource, List<TradeData> tradeDataList, double errorThreshold) throws HitInsertErrorsThresholdException {
        int listSize = tradeDataList.size();
        maxExpectedErrors = (int) Math.ceil(listSize * (errorThreshold / 100));
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String insertQuery = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                for (TradeData tradeData: tradeDataList) {
                    String tradeId = TradeRepository.getTradeId(connection, tradeData.getTrade_id());
                    if (tradeId != null) {
                        duplicateRecords++;
                        continue;
                    }
                    String tickerSymbol = TradeRepository.getTickerSymbol(connection, tradeData.getTicker_symbol());
                    if (tickerSymbol == null) {
                        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/jdbc/trade/logs/write_error_log.txt";
                        StringBuilder sb = new StringBuilder();
                        sb.append(tradeData.getTrade_id()).append(",").append(tradeData.getTrade_identifier()).append(",")
                                .append(tradeData.getTicker_symbol()).append(",").append(tradeData.getQuantity()).append(",")
                                .append(tradeData.getPrice()).append(",").append(tradeData.getTrade_date());
                        WriteErrorLogs.writeErrorLogsToFile("[" + sb.toString() + "] - Invalid security symbol: [" + tradeData.getTicker_symbol() + "].", filePath, tradeData.getLine_no());
                        insertErrorCount++;
                        continue;
                    }

                    preparedStatement = prepareInsertStatement(preparedStatement, tradeData);
                    preparedStatement.addBatch();
                    insertSuccessCount++;
                }
                preparedStatement.executeBatch();

                if (maxExpectedErrors <= insertErrorCount) {
                    System.out.println("maxExpectedErrors=" + maxExpectedErrors);
                    System.out.println("insertErrorCount=" + insertErrorCount);
                    connection.rollback();
                    throw new HitInsertErrorsThresholdException("Hits maximum error threshold while writing to db..." + insertErrorCount);
                }
                connection.commit();
                printSummary(listSize);
            } catch (BatchUpdateException e) {
                System.out.println("BatchUpdateException>>>" + e.getMessage());

                int[] updateCounts = e.getUpdateCounts();
                insertSuccessCount = 0;
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
                printSummary(listSize);
            } catch (SQLException e) {
                System.out.println("SQLException in DB Prepare statement>>> " + e.getMessage());
                connection.rollback();
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException("ParseException>>> " + e);
            } catch (HitInsertErrorsThresholdException e) {
                System.err.println("HitInsertErrorsThresholdException>>> " + insertErrorCount);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("SQLException in DB connection>>> " + e.getMessage());
            e.printStackTrace();
        }
    }
}
