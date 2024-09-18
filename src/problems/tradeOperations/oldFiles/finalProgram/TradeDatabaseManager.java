package problems.tradeOperations.oldFiles.finalProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TradeDatabaseManager {
    private DatabaseConnection databaseConnection;

    public TradeDatabaseManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public static int[] processBatchInsert(List<String[]> validTrades, DatabaseConnection databaseManager) {
        String insertQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";
        int successfulInserts = 0;
        int failedInserts = 0;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            connection.setAutoCommit(false); // Start transaction

            for (String[] fields : validTrades) {
                statement.setString(1, fields[0]);
                statement.setString(2, fields[1]);
                statement.setInt(3, Integer.parseInt(fields[2]));
                statement.setDouble(4, Double.parseDouble(fields[3]));
                statement.setString(5, fields[4]);

                statement.addBatch();
            }

            int[] result = statement.executeBatch();
            connection.commit(); // Commit transaction if all succeed

            for (int res : result) {
                if (res >= 0) {
                    successfulInserts++;
                } else {
                    failedInserts++;
                }
            }
            System.out.println("Inserted valid trades into the database.");
        } catch (SQLException e) {
            System.out.println("Error during batch insertion: " + e.getMessage());
            failedInserts += validTrades.size(); // Consider all failed if an error occurs
        }

        return new int[]{successfulInserts, failedInserts};
    }
}
