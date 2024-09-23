package JDBC.trades.repo;

import JDBC.trades.exceptions.HitErrorsThresholdException;
import JDBC.trades.main.TradesMain;
import JDBC.trades.model.TradePOJO;
import JDBC.trades.services.TradesService;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class TradeRepo {
    private static final Logger log = LoggerFactory.getLogger(TradeRepo.class);
    static int batchSize = 250;
    public static int batchElement = 0;
    static int errorLineNumber = 0;

    public static void writerErrorLogs(String exception, String errorLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/insertion_error_log.txt", true))) {
            writer.write("[ERROR]: " + exception + " [ERROR-LINE#]: " + errorLineNumber + " [ERROR LINE]: " + errorLine + " [TIME]: " + LocalDateTime.now() + "\n");
        } catch (IOException e) {

        }
    }

    public static int processBatch(List<TradePOJO> datas, HikariDataSource dataSource) {
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            String currentValue = "";
            try {
                for (TradePOJO tradePOJO : datas) {
                    errorLineNumber++;
                    currentValue = tradePOJO.toString();
                    boolean correct = checkTickerSymbol(tradePOJO.getTicker_symbol(), connection, tradePOJO);
                    Integer x = processBatch(datas, tradePOJO, preparedStatement, correct);
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

    private static Integer processBatch(List<TradePOJO> datas, TradePOJO tradePOJO, PreparedStatement preparedStatement, boolean correct){
        System.out.println(TradesService.threshold +""+ TradesService.thresholdPercent);
        if (TradesService.threshold == TradesService.thresholdPercent) {
            batchElement = 0;
            return 0;
        }
        try {
            preparedStatement.setString(1, tradePOJO.getTrade_id());
            preparedStatement.setString(2, tradePOJO.getTrade_identifier());
            preparedStatement.setString(3, tradePOJO.getTicker_symbol());
            preparedStatement.setInt(4, tradePOJO.getQuantity());
            preparedStatement.setDouble(5, tradePOJO.getPrice());
            preparedStatement.setDate(6, tradePOJO.getDate());
            if (!correct) {
                preparedStatement.addBatch();
                TradesService.insertions++;
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
                    TradesService.threshold++;
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
}
