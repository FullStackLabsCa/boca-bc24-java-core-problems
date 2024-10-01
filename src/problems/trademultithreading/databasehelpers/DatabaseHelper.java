package problems.trademultithreading.databasehelpers;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;

public class DatabaseHelper {
    private DatabaseHelper(){

    }
    public static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/studentDB");
        config.setUsername("root");
        config.setPassword("password123");
        config.setMaximumPoolSize(10); // Set max connections in pool
        config.setConnectionTimeout(3000); // Timeout in milliseconds
        config.setIdleTimeout(1000); // Idle timeout before connection is closed

        // Create the HikariCP data source
        dataSource = new HikariDataSource(config);

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


//private static final String URL = "jdbc:mysql://localhost:3306/bootcamp";
//private static final String USER = "root";
//private static final String PASSWORD = "password123";
//
//public static Connection getConnection() throws SQLException {
//    return DriverManager.getConnection(URL, USER, PASSWORD);