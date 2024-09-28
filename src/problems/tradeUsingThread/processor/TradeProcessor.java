package problems.tradeUsingThread.processor;

import problems.tradeUsingThread.databaseConnection.HikariCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeProcessor implements Runnable {

    String lines;
    String symbol;
    public int totalrowsInsertedInJournalTable= 0;
    LinkedBlockingQueue<String> queue;
    List<String> payload = new ArrayList<>();

    public TradeProcessor(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            processQueue(queue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void processQueue(LinkedBlockingQueue<String> queue) throws Exception {
        while (!queue.isEmpty()) {
            String trade_id = queue.take();
            getPayload(trade_id);
        }
    }

    public void getPayload(String trade_id) throws Exception {
        String query = "SELECT payload FROM trade_payloads WHERE trade_id= ? AND status= true";
        try (Connection connection = HikariCP.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, trade_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                lines = rs.getString("payload");
                lookSecurityTable(lines);
//                insertToJournalTable(lines, symbol);

            }
        }
    }

    public void lookSecurityTable(String lines) {
        String[] fields = lines.split(",");
        String query = "SELECT cusip FROM securities_reference WHERE cusip= ?";
        try (Connection connection = HikariCP.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fields[3]);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                symbol = rs.getString("cusip");
                insertToJournalTable(lines, symbol);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertToJournalTable(String lines, String symbol) {
        String insertToJournalQuery = "INSERT INTO journal_entry (account_no, direction, quantity) VALUES (?, ?, ?)";
        String[] fields = lines.split(",");
        if (symbol != null) {
            try (Connection connection = HikariCP.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertToJournalQuery);
                preparedStatement.setString(1, fields[2]);
                preparedStatement.setString(2, fields[4]);
                preparedStatement.setInt(3, Integer.parseInt(fields[5]));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
