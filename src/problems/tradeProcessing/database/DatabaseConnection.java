package problems.tradeProcessing.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnection {

    private static HikariDataSource dataSource;

    public DatabaseConnection(String portal, String dbName) {
        configureHikariCP(portal, dbName);
    }

    // Configure HikariCP connection pool
    public static void configureHikariCP(String port, String databaseName) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:" + port + "/" + databaseName);
        config.setUsername("root");
        config.setPassword("password123");

        // Optional HikariCP settings
        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    // Add a method to check if the data source is initialized
    public static boolean isInitialized() {
        return dataSource != null;
    }

}
