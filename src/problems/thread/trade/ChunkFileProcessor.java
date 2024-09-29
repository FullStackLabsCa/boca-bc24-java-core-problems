package problems.thread.trade;

import problems.thread.trade.database.DatabaseConnectionPool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChunkFileProcessor implements Runnable {
    private String chunkPath;

    private static Connection connection;

    public ChunkFileProcessor(String chunkPath) {
        this.chunkPath = chunkPath;
    }

    @Override
    public void run() {
        insertInRawTable(chunkPath);
    }

    static {
        try {
            connection = DatabaseConnectionPool.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertInRawTable(String fileName) {
        String insertQuery = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
        String line;
        int queueNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] payload = line.split(",");
                //insert to trade_payloads table
                insertIntoTradePayloadsTable(preparedStatement, payload, line);
                //insert into queue Distributor Map
                queueNumber = getUniqueQueueNumber();
                ThreadTradeService.queueDistributorConcurrentHashMap.put(payload[2], queueNumber);
                //writes to queue
                writesToQueues(queueNumber, payload);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void writesToQueues(int queueNumber, String[] payload) {
        if (queueNumber == 1) {
            ThreadTradeService.queue1.add(payload[0]);
        }
        if (queueNumber == 2) {
            ThreadTradeService.queue2.add(payload[0]);
        }
        if (queueNumber == 3) {
            ThreadTradeService.queue3.add(payload[0]);
        }
    }

    public static int getUniqueQueueNumber() {
        return (int) (Math.random() * 3) + 1;
    }

    private static void insertIntoTradePayloadsTable(PreparedStatement preparedStatement, String[] payload, String line) throws SQLException {
        preparedStatement.setString(1, payload[0]);
        preparedStatement.setString(2, "valid");
        preparedStatement.setString(3, line);
        preparedStatement.executeUpdate();
    }
}
