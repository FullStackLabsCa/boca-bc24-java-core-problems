package tradeprocessor.tradereader;

import tradeprocessor.dbconnection.TradingDatabseConnectionPool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ChunkProcessor implements Runnable {
    Connection conn;
    static LinkedBlockingDeque<String> queue1 = new LinkedBlockingDeque<>();
    static LinkedBlockingDeque<String> queue2 = new LinkedBlockingDeque<>();
    static LinkedBlockingDeque<String> queue3 = new LinkedBlockingDeque<>();
    List<LinkedBlockingDeque<String>> queues = new ArrayList<>();
    Random rand = new Random();
    static ConcurrentHashMap<String, String> queueDistributerMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, String> getQueueDistributerMap() {
        return queueDistributerMap;
    }

    public static LinkedBlockingDeque<String> getQueue1() {
        return queue1;
    }

    public static LinkedBlockingDeque<String> getQueue2() {
        return queue2;
    }

    public static LinkedBlockingDeque<String> getQueue3() {
        return queue3;
    }

    static String filePath;


    public ChunkProcessor(String filePath) throws Exception {
        this.conn = TradingDatabseConnectionPool.getConnection();
        queues.add(queue1);
        queues.add(queue2);
        queues.add(queue3);
        this.filePath = filePath;
    }


    @Override
    public void run() {
        try {
            processChunk();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void processChunk() throws InterruptedException {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                RawTradePayLoad rawTradePayLoad = createRawTradePayload(line);
                String[] columns = line.split(",");
                boolean isValid = true;
                if (columns.length != 7) {
                    isValid = false;
                }
                String status = isValid ? "VALID" : "INVALID";
                insertIntoDataBase(columns[0], line, status);
                insertIntoHashMap(columns[2]);
                insertIntoQueue(columns[2], columns[0]);
            }


        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


    }

    public void insertIntoDataBase(String tradeId, String payload, String status) throws SQLException {
        String query = "INSERT INTO trade_payload (trade_id, payload, status) VALUES (?, ?, ?) " +
                " ON DUPLICATE KEY UPDATE " +
                " status = VALUES(status), " +
                " payload = VALUES(payload)";
//        String query = "INSERT INTO trade_payload (trade_id,status,payload) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tradeId);
            ps.setString(2, payload);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void insertIntoHashMap(String accountNumber) {
        String randomNumber = String.valueOf(rand.nextInt(3) + 1);
        while (!queueDistributerMap.containsKey(accountNumber)) {
            queueDistributerMap.put(accountNumber, randomNumber);

        }

    }

    public void insertIntoQueue(String accountNumber, String tradeId) {
        if ("1".equals(queueDistributerMap.get(accountNumber))) {
            queue1.add(tradeId);
        }
        if ("2".equals(queueDistributerMap.get(accountNumber))) {
            queue2.add(tradeId);
        }
        if ("3".equals(queueDistributerMap.get(accountNumber))) {
            queue3.add(tradeId);
        }
    }

}