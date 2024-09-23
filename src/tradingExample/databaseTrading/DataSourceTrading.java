package tradingExample.databaseTrading;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceTrading {
    public static HikariDataSource configureHikariCP(String port, String databaseName) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:"+port+"/"+databaseName);
        config.setUsername("root");
        config.setPassword("password123");


        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);

        return new HikariDataSource(config);
    }
}




