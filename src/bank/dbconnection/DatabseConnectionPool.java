package bank.dbconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class DatabseConnectionPool {
    private static HikariDataSource dataSource;

    static {
        // Configure the HikariCP connection pool
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        config.setUsername("root");
        config.setPassword("Password123");
        config.setMaximumPoolSize(10); // Set max connections in pool
        config.setConnectionTimeout(30000); // Timeout in milliseconds
        config.setIdleTimeout(10000); // Idle timeout before connection is closed

        // Create the HikariCP data source
        dataSource = new HikariDataSource();
    }

    public static Connection getConnection() throws Exception {
        // Fetch a connection from the pool
        return dataSource.getConnection();
    }

    public static void close() {
        // Close the data source (usually when the app shuts down)
        if (dataSource != null) {
            dataSource.close();
        }

}





}
