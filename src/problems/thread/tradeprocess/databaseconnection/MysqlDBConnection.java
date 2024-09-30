package thread.tradeprocess.databaseconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import thread.tradeprocess.tradecontract.DatabaseConnection;

public class MysqlDBConnection implements DatabaseConnection {
    public HikariDataSource configureHikariCP(String url) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername("root");
        config.setPassword("password123");

        config.setMaximumPoolSize(10); // Max 10 connections in the pool
        config.setMinimumIdle(5); // Minimum idle connections
        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout
        return new HikariDataSource(config);
    }
}
