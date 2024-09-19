package trading_parser.utility;

import java.sql.*;

public class BatchFailureTrackingExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bootcamp";
        String user = "root";
        String password = "password123";


        Object[][] tradesData = {
                {1, "AAPL", 100, 150.00, "2023-09-15"},
                {2, "GOOGL", 50, 2800.00, "2023-09-16"},
                {3, "MSFT", 75, 300.00, "2023-09-17"},
                {3, "ER", 100, 250.00, "2023-09-18"},
                {2, "ER", 100, 250.00, "2023-09-18"}
        };


        String sql = """
                    insert into Trades (trade_id, ticker_symbol, quantity, price, trade_date)
                    values (?, ?, ?, ?, ?)
                    """;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false); // Start transaction

            // Add trades to the batch
            for (Object[] trade : tradesData) {
                pstmt.setString(1, String.valueOf(trade[0])); // trade_id
                pstmt.setString(2, (String) trade[1]); // ticker_symbol
                pstmt.setInt(3, (Integer) trade[2]); // quantity
                pstmt.setDouble(4, (Double) trade[3]); // price
                pstmt.setString(5, (String) trade[4]); // trade_date
                pstmt.addBatch(); // Add to batch
            }

            try {
                // Execute the batch
                int[] results = pstmt.executeBatch();
                connection.commit(); // Commit transaction
            } catch (BatchUpdateException e) {
                int[] updateCounte = e.getUpdateCounts();
                System.err.println("Batch execution failed: " + e.getMessage());
//                e.printStackTrace();
                connection.rollback(); // Rollback if there's an error
                System.out.println("Transaction rolled back.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
}
