package io.reactivestax.databaseconnector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnector {

    public static HikariDataSource configureHikariCP(){
        HikariDataSource dataSource;
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/TradesWithMultiThreading");
        config.setUsername("root");
        config.setPassword("password123");
        config.setMaximumPoolSize(100); // Max 10 connections in the pool
        config.setMinimumIdle(100); // Minimum idle connections
        config.setConnectionTimeout(100000); // 30 seconds timeout for obtaining a connection
        config.setIdleTimeout(600000); // 10 minutes idle timeout
        dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
