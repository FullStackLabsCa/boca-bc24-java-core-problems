package transaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnection {

   static HikariConfig config = new HikariConfig();
    public static HikariDataSource dataSource;


    public static void configureHikariCP() {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/Bank");
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
        if (dataSource == null) {
            throw new SQLException("DataSource is not initialized. Call configureHikariCP() first.");
        }
        return dataSource.getConnection();
    }
}

