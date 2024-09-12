package problems.selfPractice.connectionPooling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// This class manages the connection pool. It uses HikariCP to handle connections efficiently.

public class DataSourceFactory {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/employeeTemp");
        config.setUsername("root");
        config.setPassword("password123");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5000);
        config.setIdleTimeout(60000); // 60 sec
        config.setConnectionTimeout(30000); // 30 sec
        config.setLeakDetectionThreshold(30000); // 30 sec : detect leaks if conncection is held for morethan 30 sec
        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource(){
        return dataSource;
    }

}
