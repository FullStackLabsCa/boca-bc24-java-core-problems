package problems.tradeUsingThread.processor;

import problems.tradeUsingThread.databaseConnection.HikariCP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ChunkProcessor implements Runnable {
    private String filePath;

    public static LinkedBlockingQueue queue1 = new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue queue2 = new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue queue3 = new LinkedBlockingQueue<>();

    public static ConcurrentHashMap<String, String> tradeQueueMap = new ConcurrentHashMap<>();

    public ChunkProcessor(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is processing file: " + filePath);
            readChunk();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readChunk() throws FileNotFoundException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                insertTradePayloadData(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                addToTradeQueueMap(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                hashMapToBlockingQueue(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertTradePayloadData(String line) throws Exception {
        String[] fields = line.split(",");
        String query = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
        try (Connection connection = HikariCP.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fields[0]);
            if (fields.length != 7 || Arrays.stream(fields).anyMatch(Objects::isNull)) {
                preparedStatement.setInt(2, 0);
            } else {
                preparedStatement.setInt(2, 1);
            }
            preparedStatement.setString(3, line);
            preparedStatement.executeUpdate();
        }
    }

    public void addToTradeQueueMap(String line) {
        int min = 1;
        int max = 4;
        String[] fields = line.split(",");
        while (!tradeQueueMap.containsKey(fields[2])) {
            tradeQueueMap.put(fields[2], String.valueOf((int) (Math.random() * (max - min) + min)));
        }
    }

    public void hashMapToBlockingQueue(String line) {
        String[] fields = line.split(",");

        if ("1".equals(tradeQueueMap.get(fields[2]))) {
            queue1.add(fields[0]);
        }
        if ("2".equals(tradeQueueMap.get(fields[2]))) {
            queue2.add(fields[0]);
        }
        if ("3".equals(tradeQueueMap.get(fields[2]))) {
            queue3.add(fields[0]);
        }
    }
}
