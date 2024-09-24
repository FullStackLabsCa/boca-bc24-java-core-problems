package problems.trade.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;


public class HikariCPConfig {

    private static HikariDataSource dataSource;

    private HikariCPConfig() {
    }

    public static HikariDataSource getDataSource() {
        if (dataSource == null) {
            synchronized (problems.creditcard.config.HikariCPConfig.class) {
                if (dataSource == null) {
                    HikariConfig config = new HikariConfig();
                    config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
                    config.setUsername("root");
                    config.setPassword("password123");
                    config.setMaximumPoolSize(10);
                    config.setConnectionTimeout(10000);
                    config.setIdleTimeout(10000);

                    dataSource = new HikariDataSource(config);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}