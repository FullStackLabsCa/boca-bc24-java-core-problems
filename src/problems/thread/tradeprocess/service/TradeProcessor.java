package thread.tradeprocess.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.optimisticlocking.exceptions.OptimisticLockingException;
import thread.tradeprocess.model.TradeProcessData;
import thread.tradeprocess.repository.TradeRepository;
import thread.tradeprocess.utils.WriteErrorLogs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeProcessor implements Runnable {
    private static HikariDataSource dataSource;
    public LinkedBlockingQueue<String> tradesQueue;

    public TradeProcessor(HikariDataSource dataSource, LinkedBlockingQueue<String> tradesQueue) {
        this.dataSource = dataSource;
        this.tradesQueue = tradesQueue;
    }

    @Override
    public void run() {
        processTradeQueue(tradesQueue);
    }
    public void processTradeQueue(LinkedBlockingQueue<String> tradesQueue) {
        while (!tradesQueue.isEmpty()) {
            try {
                String tradeId = tradesQueue.take();
                processTrade(tradesQueue, tradeId);  // Process transaction
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void processTrade(LinkedBlockingQueue<String> tradesQueue, String tradeId) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                performTradeDbOperation(connection, tradeId);
                connection.commit();
                tradesQueue.remove(tradeId);

            }
            catch (OptimisticLockingException e) {
                System.err.println(e.getMessage());
                tradesQueue.remove(tradeId);
                TradeService.tradeDeQueue.putFirst(tradeId);
            }
            catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    System.err.println("Duplicate entry detected for tradeId " + tradeId);
                }
                connection.rollback();
                tradesQueue.remove(tradeId);
                TradeService.tradeDeQueue.putFirst(tradeId);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void performTradeDbOperation(Connection connection, String tradeId) throws SQLException, InterruptedException {
        String payload = TradeRepository.getTradePayload(connection, tradeId);

        String accountNo = payload.split(",")[2].trim();
        String cusip = payload.split(",")[3].trim();
        String activity = payload.split(",")[4].trim();
        int quantity = Integer.parseInt(payload.split(",")[5].trim());
        double price = Double.parseDouble(payload.split(",")[6].trim());

        TradeProcessData tradeProcessData = new TradeProcessData(tradeId, accountNo, cusip, activity, quantity, price);

        String cusipDbResult = TradeRepository.getCusip(connection, cusip);
        if (cusipDbResult == null) {
            writeLogs(tradeProcessData);
        } else {
            TradeRepository.insertJournalEntry(connection, tradeProcessData);

            int version = TradeRepository.getPositionVersion(connection, accountNo, cusip);

            calculateTradeDirection(tradeId, connection, activity, version, tradeProcessData, accountNo, cusip);
        }
    }

    private static void writeLogs(TradeProcessData tradeProcessData) {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/thread/tradeprocess/logs/write_error_log.txt";
        String errorLog = String.join(",", tradeProcessData.getTradeId(), tradeProcessData.getAccountNo(),
                tradeProcessData.getCusip(), tradeProcessData.getActivity(),
                String.valueOf(tradeProcessData.getQuantity()), String.valueOf(tradeProcessData.getPrice()));
        WriteErrorLogs.writeErrorLogsToFile("[" + errorLog + "] - Invalid security symbol: [" + tradeProcessData.getCusip() + "].", filePath);
    }

    private static void calculateTradeDirection(String tradeId, Connection connection, String activity, int version, TradeProcessData tradeProcessData, String accountNo, String cusip) throws SQLException, InterruptedException {
        if (activity.equals("BUY")) {
            if (version == -1) {
                TradeRepository.insertPosition(connection, tradeProcessData);
            } else {
                TradeRepository.updatePosition(connection, tradeProcessData, version);
            }
        } else if (activity.equals("SELL")) {
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
}
