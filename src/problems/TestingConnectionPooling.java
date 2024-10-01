package problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestingConnectionPooling {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bootcamp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password123";

    public static void main(String[] args) {
        int maxConnections = 100;
        Connection[] connections = new Connection[maxConnections];

        try {
            for (int i = 0; i <= maxConnections; i++) {
                System.out.println("Opening connection #" + (i + 1));
                connections[i] = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                // Simulate doing something with the connection
                try (Statement stmt = connections[i].createStatement()) {
                    stmt.execute("SELECT 1");  // Simple query to keep the connection alive
                }

                // Leave the connection open to simulate exhaustion of resources
                Thread.sleep(100);  // Simulate some delay between connections
            }
        } catch (SQLException ex) {
            System.err.println("SQLException occurred: " + ex.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
                for(Connection conn : connections){
                    if(conn != null){
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        }
    }
}


