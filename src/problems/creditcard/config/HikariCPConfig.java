package problems.creditcard.config;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class HikariCPConfig {

    private static HikariDataSource dataSource;

    private HikariCPConfig() {
        // private constructor to prevent instantiation
    }

    public static HikariDataSource getDataSource() {
        if (dataSource == null) {
            synchronized (HikariCPConfig.class) {
                if (dataSource == null) {
                    HikariConfig config = new HikariConfig();
                    config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
                    config.setUsername("root");
                    config.setPassword("password123");
                    config.setMaximumPoolSize(10); // Set max connections in pool
                    config.setConnectionTimeout(10000); // Timeout in milliseconds
                    config.setIdleTimeout(10000); // Idle timeout before connection is closed

                    dataSource = new HikariDataSource(config);
                }
            }
        }
        return dataSource;
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

