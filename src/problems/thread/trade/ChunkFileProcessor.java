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

    public static Connection connection;

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
            throw new RuntimeException(e);
        }
    }

    public static void insertInRawTable(String fileName) {
        String insertQuery = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] payload = line.split(",");
                //insert to trade_payloads table
                insertIntoTradePayloadsTable(preparedStatement, payload, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertIntoTradePayloadsTable(PreparedStatement preparedStatement, String[] payload, String line) throws SQLException {
        preparedStatement.setString(1, payload[0]);
        preparedStatement.setString(2, "valid");
        preparedStatement.setString(3, line);
        preparedStatement.executeUpdate();
    }
}
