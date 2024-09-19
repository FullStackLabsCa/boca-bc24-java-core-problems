package problems.tradeOperations.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProcessBatchInsert {

     public int[] processBatchInsert(List<String[]> validTrades, DatabaseManager databaseManager) {
        String insertQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?,?)";

        Connection connection = null;
        PreparedStatement statement = null;
        int successfulInserts = 0;
        int failedInserts = 0;

        try {
            connection = databaseManager.getConnection();
            statement = connection.prepareStatement(insertQuery);

            connection.setAutoCommit(false); // Start transaction

            for (String[] fields : validTrades) {
                statement.setString(1, fields[0]);
                statement.setString(2, fields[1]);
                statement.setString(3, fields[2]);
                statement.setInt(4, Integer.parseInt(fields[3]));
                statement.setDouble(5, Double.parseDouble(fields[4]));
                statement.setString(6, fields[5]);

                statement.addBatch();
            }
            try {
                int[] result = statement.executeBatch();
                connection.commit(); // Commit transaction if all statements succeed

                for (int i : result) {
                    if (i >= 0) { // Check for successful insertions
                        successfulInserts++;
                    } else {
                        failedInserts++;
                    }
                }
                System.out.println("Inserted valid trades into the database.");
            } catch (SQLException batchException) {
                // Rollback if there is an error during batch execution
                connection.rollback();
                System.out.println("Failed to insert trade into the database: " + batchException.getMessage());
                failedInserts += validTrades.size();
            }

        } catch (SQLException e) {

            System.out.println("Error with database connection: " + e.getMessage());
        } finally {
            // Ensure resources are closed
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return new int[]{successfulInserts, failedInserts};
    }

}
