package problems.creditcardtransaction.oopimplemented.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPConfig {
    private static HikariDataSource dataSource;

    public static void configureHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
        config.setUsername("root");
        config.setPassword("password123");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
