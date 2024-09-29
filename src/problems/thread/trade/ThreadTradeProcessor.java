package problems.thread.trade;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import problems.thread.trade.database.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("java:S106")
public class ThreadTradeProcessor implements Runnable {
    private LinkedBlockingQueue<String> queue;

    public ThreadTradeProcessor(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            getTradeIdFromQueue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getTradeIdFromQueue() throws Exception {
        String tradeIdStoredInQueue = "";
        while (!queue.isEmpty()) {
            tradeIdStoredInQueue = queue.take();
            getDataFromTradePayloadsTable(tradeIdStoredInQueue);
        }
    }

    public static void getDataFromTradePayloadsTable(String tradeId) throws Exception {
        String fetchData;
        String query = "SELECT * FROM trade_payloads WHERE trade_id = ?";
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fetchData = resultSet.getString("payload");
                String[] payloadArray = fetchData.split(",");
                writeToJournalTable(payloadArray);
            }
        }
    }

    public static void writeToJournalTable(String[] array) throws SQLException {
        String insertQuery = "INSERT INTO journal_entries (account_number, cusip, direction, quantity, price,posted_date) VALUES (?,?,?,?,?,?)";
        String lookupQuery = "SELECT symbol FROM SecuritiesReference WHERE symbol = ?";
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
             PreparedStatement lookupStatement = connection.prepareStatement(lookupQuery);) {
            connection.setAutoCommit(false);

            String symbol = "";
            //checking the security symbol
            lookupStatement.setString(1, array[3]);
            ResultSet resultSet = lookupStatement.executeQuery();
            if (resultSet.next()) {
                symbol = resultSet.getString("symbol");

                //inserting to the journal entry table
                insertStatement.setString(1, array[2]);
                insertStatement.setString(2, array[3]);
                insertStatement.setString(3, array[4]);
                insertStatement.setString(4, array[5]);
                insertStatement.setString(5, array[6]);
                insertStatement.setString(6, array[1]);
                insertStatement.executeUpdate();

                //write to the positions table


            }
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToPositionsTable(String[] array){

    }
}
