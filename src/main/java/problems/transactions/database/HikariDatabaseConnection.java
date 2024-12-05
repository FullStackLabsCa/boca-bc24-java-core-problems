//package problems.transactions.database;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//
//
//public class HikariDatabaseConnection {
//    // HikariCP DataSource
//    public static HikariDataSource dataSource;
//
//     //Configure HikariCP connection pool
//    private static void configureHikariCP() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/bootcamp");
//        config.setUsername("root");
//        config.setPassword("password123");
//
//        // Optional HikariCP settings
//        config.setMaximumPoolSize(10); // Max 10 connections in the pool
//        config.setMinimumIdle(5); // Minimum idle connections
//        config.setConnectionTimeout(30000); // 30 seconds timeout for obtaining a connection
//        config.setIdleTimeout(600000); // 10 minutes idle timeout
//
//        dataSource = new HikariDataSource(config);
//    }
//}
