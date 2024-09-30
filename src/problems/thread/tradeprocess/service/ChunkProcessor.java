package thread.tradeprocess.service;

import com.zaxxer.hikari.HikariDataSource;
import thread.tradeprocess.repository.TradeRepository;
import thread.tradeprocess.utils.ValidateFileData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ChunkProcessor implements Runnable {

    private final HikariDataSource dataSource;
    private final String filePath;

    public ChunkProcessor(HikariDataSource dataSource, String filePath) {
        this.dataSource = dataSource;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        processChunk();
    }

    public void processChunk() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
              boolean isValidFileRow = ValidateFileData.validateFileRow(line);
              String status;
                if (!isValidFileRow) {
                    status = "invalid";
                } else {
                    status = "valid";
                }

                String tradeId = line.split(",")[0];
                String accountNo = line.split(",")[2];

                boolean isInserted = insertToDBTradePayloadTable(dataSource, tradeId, status, "status_reason", line);

                if (isInserted) {
                    String queueNo = TradeDistributors.addToConcurrentHashMap(accountNo);
                    System.out.println("queueNo = " + queueNo + " tradeId::"+tradeId);
                    TradeDistributors.addToLinkedBlockingQueue(queueNo, tradeId);
                }
                TradeDistributors.printMapAndQueue();
            }
        } catch (IOException e) {
            System.out.println("IOEXCEPTION>>>>>>>>" + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
//            e.printStackTrace();
        }
    }

    public boolean insertToDBTradePayloadTable(HikariDataSource dataSource, String tradeId,  String status, String statusReason, String tradePayload) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String dbTradeId = TradeRepository.getTradeId(connection, tradeId);
                if (dbTradeId == null) {
                    TradeRepository.insertTradePayload(connection, tradeId, status, statusReason, tradePayload);
                } else {
                    return false;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
