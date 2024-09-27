package practice.multi_threading.trading_processing_assignment.connection_pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {

    private HikariDataSource dataSource;

    public HikariCPDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/trade_multipthreading");
        config.setUsername("root");
        config.setPassword("password123");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);  // Adjust based on system capacity
        config.setMinimumIdle(2);       // Minimum idle connections
        config.setIdleTimeout(30000);   // Timeout for idle connections
        config.setMaxLifetime(600000);  // Maximum connection lifetime
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
