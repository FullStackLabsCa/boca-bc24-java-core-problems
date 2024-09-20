package trading;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    static HikariConfig hikariConfig = new HikariConfig();
    public static HikariDataSource dataSource;

    public static void configureHikariCP() {

        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/trade");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("password123");

        hikariConfig.setIdleTimeout(6000000);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setConnectionTimeout(300000);

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() {
        try {
            if (dataSource != null) {
                return dataSource.getConnection();
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
