package thread.tradeprocess.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.optimisticlocking.exceptions.OptimisticLockingException;
import thread.tradeprocess.model.TradeProcessData;
import thread.tradeprocess.repository.TradeRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeRetry {
    public static void retryTrades(HikariDataSource dataSource, LinkedBlockingDeque<String> tradesDeQueue) {
        System.out.println("tradesDeQueue=========" + tradesDeQueue.size());
        while (!tradesDeQueue.isEmpty()) {
            try {
                String tradeId = tradesDeQueue.take();
                retryTradeProcess(dataSource, tradeId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void retryTradeProcess(HikariDataSource dataSource, String tradeId) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                performTradeDbRetryOperation(connection, tradeId);
                connection.commit();

            }
            catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                TradeService.tradeDeQueue.putFirst(tradeId);
            }
            catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    System.err.println("Duplicate entry detected for tradeId " + tradeId);
                }
                connection.rollback();
                TradeService.tradeDeQueue.putFirst(tradeId);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void performTradeDbRetryOperation(Connection connection, String tradeId) throws SQLException, InterruptedException {
        String payload = TradeRepository.getTradePayload(connection, tradeId);

        String accountNo = payload.split(",")[2].trim();
        String cusip = payload.split(",")[3].trim();
        String activity = payload.split(",")[4].trim();
        int quantity = Integer.parseInt(payload.split(",")[5].trim());
        double price = Double.parseDouble(payload.split(",")[6].trim());

        TradeProcessData tradeProcessData = new TradeProcessData(tradeId, accountNo, cusip, activity, quantity, price);

        int version = TradeRepository.getPositionVersion(connection, accountNo, cusip);
        if (version == -1) {
            TradeService.tradeDeQueue.putFirst(tradeId);
        } else {
            int availablePosition = TradeRepository.getPosition(connection, accountNo, cusip);
            int positionToSell = tradeProcessData.getQuantity();

            if (availablePosition >= positionToSell) {
                TradeRepository.updatePosition(connection, tradeProcessData, version);
            } else {
                TradeService.tradeDeQueue.putFirst(tradeId);
            }
        }
    }
}
