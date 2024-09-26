package multithreading.Trades.repo;

import jdbc.trades.exceptions.HitErrorsThresholdException;
import jdbc.trades.model.TradePOJO;
import jdbc.trades.services.TradesService;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class TradeRepo implements TradeRepoInterface {
    private static final Logger log = LoggerFactory.getLogger(TradeRepo.class);
    public static int batchElement = 0;
    static int batchSize = 250;
    static int errorLineNumber = 0;

    public static void writerErrorLogs(String exception, String errorLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/insertion_error_log.txt", true))) {
            writer.write("[ERROR]: " + exception + " [ERROR-LINE#]: " + errorLineNumber + " [ERROR LINE]: " + errorLine + " [TIME]: " + LocalDateTime.now() + "\n");
        } catch (IOException e) {

        }
    }

    private static Integer addtoBatch(List<TradePOJO> datas, TradePOJO tradePOJO, PreparedStatement preparedStatement, boolean correct) {
      //  System.out.println(TradesService.getThreshold() + "" + TradesService.getThresholdPercent());
        if (TradesService.getThreshold() == TradesService.getThresholdPercent()) {
            batchElement = 0;
            return 0;
        }
        try {
            preparedStatement.setString(1, tradePOJO.getTradeId());
            preparedStatement.setString(2, tradePOJO.getTradeIdentifier());
            preparedStatement.setString(3, tradePOJO.getTickerSymbol());
            preparedStatement.setInt(4, tradePOJO.getQuantity());
            preparedStatement.setDouble(5, tradePOJO.getPrice());
            preparedStatement.setDate(6, tradePOJO.getDate());
            if (!correct) {
                preparedStatement.addBatch();
                TradesService.setInsertions(TradesService.getInsertions() + 1);
            }
            batchElement++;
            if (batchElement >= batchSize) {
                System.out.println("Executing Batch");
                preparedStatement.executeBatch();
                batchElement = 0;
            } else if (datas.size() - 1 == datas.indexOf(tradePOJO)) {
                preparedStatement.executeBatch();
                batchElement = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static boolean checkTickerSymbol(String tickerSymbol, Connection connection, TradePOJO tradePOJO) {
        boolean status = false;
        String symbol = "empty";
        String query = "SELECT symbol FROM SecuritiesReference WHERE symbol= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tickerSymbol);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (resultSet.next()) {
                    symbol = resultSet.getString("symbol");
                }
                System.out.println(symbol + " " + tickerSymbol);
                if (!symbol.equals(tickerSymbol)) {
                    TradesService.setThreshold(TradesService.getThreshold() + 1);
                    status = true;
                    writerErrorLogs("Ticker Symbol doesn't Exist", tradePOJO.toString());
                    throw new HitErrorsThresholdException("Ticker Symbol doesn't Exist");
                }
            } catch (Exception e) {
                writerErrorLogs(e.toString(), tradePOJO.toString());
                System.out.println(e);
            }
        } catch (SQLException e) {
            writerErrorLogs(e.toString(), tradePOJO.toString());
            log.error("e: ", e);
        }
        return status;
    }

    @Override
    public int processBatch(List<TradePOJO> datas, HikariDataSource dataSource) {
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            String currentValue = "";
            try {
                for (TradePOJO tradePOJO : datas) {
                    errorLineNumber++;
                    currentValue = tradePOJO.toString();
                    boolean correct = checkTickerSymbol(tradePOJO.getTickerSymbol(), connection, tradePOJO);
                    Integer x = addtoBatch(datas, tradePOJO, preparedStatement, correct);
                    if (x != null) return x;
                }
                System.out.println("Adding all the Values to the DB");
                connection.commit();
            } catch (BatchUpdateException e) {
                writerErrorLogs(e.toString(), currentValue);
                log.error("e: ", e);
                connection.rollback();
                return 0;

            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return 1;
    }
}
