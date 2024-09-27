package jdbc.trades.repo;

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
    private static int batchElement = 0;
    static int batchSize = 250;
    static int errorLineNumber = 0;

    public static void writerErrorLogs(String exception, String errorLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/insertion_error_log.txt", true))) {
            writer.write("[ERROR]: " + exception + " [ERROR-LINE#]: " + errorLineNumber + " [ERROR LINE]: " + errorLine + " [TIME]: " + LocalDateTime.now() + "\n");
        } catch (IOException e) {
            log.info("Error", e);
        }
    }

    private static Integer addtoBatch(List<TradePOJO> datas, TradePOJO tradePOJO, PreparedStatement preparedStatement, boolean correct) {
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
            }
          batchElement++;
            if (batchElement >= batchSize) {
                log.info("Executing Batch");
                preparedStatement.executeBatch();
              batchElement = 0;
            } else if (datas.size() - 1 == datas.indexOf(tradePOJO)) {
                preparedStatement.executeBatch();
              batchElement = 0;
            }
        } catch (SQLException e) {
            System.out.println("Exception: "+ e);
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
            status = isTickerPresent(tickerSymbol, tradePOJO, resultSet, symbol, status);
        } catch (SQLException e) {
            TradesService.setThreshold(TradesService.getThreshold() +1);
             writerErrorLogs(e.toString(), tradePOJO.toString());
            System.out.println("Exception: "+ e);
        }
        return status;
    }

    private static boolean isTickerPresent(String tickerSymbol, TradePOJO tradePOJO, ResultSet resultSet, String symbol, boolean status) {
        try {
            if (resultSet.next()) {
                symbol = resultSet.getString("symbol");
            }
            log.info(symbol, " ", tickerSymbol);
            if (!symbol.equals(tickerSymbol)) {
                TradesService.setThreshold(TradesService.getThreshold() + 1);
                status = true;
                writerErrorLogs("Ticker Symbol doesn't Exist", tradePOJO.toString());
                throw new HitErrorsThresholdException("Ticker Symbol doesn't Exist");
            }
            TradesService.setInsertions(TradesService.getInsertions() +1);

        } catch (Exception e) {
          writerErrorLogs(e.toString(), tradePOJO.toString());
            System.out.println("Exception: "+ e);
        }
        return status;
    }

    @Override
    public int processBatch(List<TradePOJO> datas, HikariDataSource dataSource) {
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            String currentValue = "";
            Integer x = commitOrRollbackBatch(datas, currentValue, connection, preparedStatement);
            if (x != null) return x;
        } catch (Exception e) {
            System.out.println("Exception: "+ e);
        }
        return 1;
    }

    private static Integer commitOrRollbackBatch(List<TradePOJO> datas, String currentValue, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        try {
            for (TradePOJO tradePOJO : datas) {
                errorLineNumber++;
                currentValue = tradePOJO.toString();
                boolean correct = checkTickerSymbol(tradePOJO.getTickerSymbol(), connection, tradePOJO);
                Integer x = addtoBatch(datas, tradePOJO, preparedStatement, correct);
                if (x != null) return x;
            }
            log.info("Adding all the Values to the DB");
            connection.commit();
        } catch (BatchUpdateException e) {
            writerErrorLogs(e.toString(), currentValue);
            System.out.println("Exception: "+ e);
            connection.rollback();
            return 0;
        }
        return null;
    }
}
