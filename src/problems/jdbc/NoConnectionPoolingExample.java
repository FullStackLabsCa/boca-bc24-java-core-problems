package problems.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NoConnectionPoolingExample {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password123";

    public static void main(String[] args) {
        int maxConnections = 100;  // Simulating a large number of connections
        Connection[] connections = new Connection[maxConnections];

        try {
            for (int i = 0; i < maxConnections; i++) {
                System.out.println("Opening connection #" + (i + 1));
                connections[i] = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                // Simulate doing something with the connection
                try (Statement stmt = connections[i].createStatement()) {
                    stmt.execute("SELECT 1");  // Simple query to keep the connection alive
                }

                // Leave the connection open to simulate exhaustion of resources
                Thread.sleep(100);  // Simulate some delay between connections
            }

        } catch (SQLException e) {
            System.err.println("SQLException occurred: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Close all connections in the end
            for (Connection conn : connections) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}