package multithreadingtrade.databaseconnectivity;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DBUtils {

    private static DBUtils instance;

    private HikariDataSource dataSource;

    private DBUtils() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
        config.setUsername("root");
        config.setPassword("password123");
        config.setMaximumPoolSize(100);
        dataSource = new HikariDataSource(config);

        // Optional HikariCP settings
//        config.setMinimumIdle(5);
//        config.setConnectionTimeout(30000);
//        config.setIdleTimeout(600000);
    }
    public static synchronized DBUtils getInstance() {
        if(instance==null) {
            instance = new DBUtils();
        }
        return instance;
    }

    public  Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}