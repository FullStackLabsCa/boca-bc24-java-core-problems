package tradeprocessor.dbconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class TradingDatabseConnectionPool {
    private static final HikariDataSource dataSource;

    static {
        // Configure the HikariCP connection pool
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
        config.setUsername("root");
        config.setPassword(System.getenv("DB_PASSWORD"));
        config.setMaximumPoolSize(100); // Set max connections in pool
        config.setConnectionTimeout(30000); // Timeout in milliseconds
        config.setIdleTimeout(10000); // Idle timeout before connection is closed

        // Create the HikariCP data source
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
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
