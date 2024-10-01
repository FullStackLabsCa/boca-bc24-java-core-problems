package problems.tradeUsingThread.processor;

import problems.tradeUsingThread.databaseConnection.HikariCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeProcessor implements Runnable {

    String lines;
    String symbol;
    int security_id;

    static int tempPos;

    LinkedBlockingQueue<String> queue;

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
            }
        }
    }

    public void lookSecurityTable(String lines) {
        String[] fields = lines.split(",");
        String query = "SELECT cusip, security_id FROM securities_reference WHERE cusip= ?";
        try (Connection connection = HikariCP.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fields[3]);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                symbol = rs.getString("cusip");
                security_id = rs.getInt("security_id");
                insertToJournalTable(lines, symbol, security_id);
                getAccAndSec(lines, security_id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertToJournalTable(String lines, String symbol, int security_id) {
        String insertToJournalQuery = "INSERT INTO journal_entry (account_no, security_id, direction, quantity) VALUES (?, ?, ?, ?)";
        String[] fields = lines.split(",");
        if (symbol != null) {
            try (Connection connection = HikariCP.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertToJournalQuery);
                preparedStatement.setString(1, fields[2]);
                preparedStatement.setInt(2, security_id);
                preparedStatement.setString(3, fields[4]);
                preparedStatement.setInt(4, Integer.parseInt(fields[5]));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getAccAndSec(String lines, int security_id) {
        String checkPresent = "SELECT position FROM position_table WHERE account_no= ? AND security_id= ?";
        String[] fields = lines.split(",");
        try (Connection connection = HikariCP.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(checkPresent);
            preparedStatement.setString(1, fields[2]);
            preparedStatement.setInt(2, security_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tempPos = rs.getInt("position");
                update_Position_table(lines, security_id, tempPos);
            } else {
                insert_Position_table(lines, security_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insert_Position_table(String lines, int security_id) throws Exception {
        String insertToPositionQuery = "INSERT INTO position_table (account_no, security_id, position, version) VALUES (?, ?, ?, 0)";
        String[] fields = lines.split(",");

        if (symbol != null) {
            try (Connection connection = HikariCP.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertToPositionQuery);
                preparedStatement.setString(1, fields[2]);
                preparedStatement.setInt(2, security_id);
                if (fields[4].equals("SELL")) {
                    preparedStatement.setInt(3, -Integer.parseInt(fields[5]));
                } else {
                    preparedStatement.setInt(3, Integer.parseInt(fields[5]));
                }

                preparedStatement.executeUpdate();
            }
        }
    }

    public void update_Position_table(String lines, int security_id, int tempPos) throws Exception {
        String updateToPositionQuery = "Update position_table SET position = ? WHERE account_no= ? AND security_id= ?";
        String[] fields = lines.split(",");

        if (symbol != null) {
            try (Connection connection = HikariCP.getConnection()) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(updateToPositionQuery);
                    if (fields[4].equals("BUY")) {
                        preparedStatement.setInt(1, (tempPos + Integer.parseInt(fields[5])));
                    }
                    if (fields[4].equals("SELL")) {
                        preparedStatement.setInt(1, (tempPos - Integer.parseInt(fields[5])));
                    }
                    preparedStatement.setString(2, fields[2]);
                    preparedStatement.setInt(3, security_id);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
