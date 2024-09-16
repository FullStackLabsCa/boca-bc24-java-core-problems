package problems.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHelper {
    public static HikariDataSource dataSource;
    private static final String URL = "jdbc:mysql://localhost:3306/bootcamp";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); // Set max connections in pool
        config.setConnectionTimeout(30000); // Timeout in milliseconds
        config.setIdleTimeout(10000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        // Close the data source (usually when the app shuts down)
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}