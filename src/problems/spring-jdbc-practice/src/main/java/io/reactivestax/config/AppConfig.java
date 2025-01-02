package io.reactivestax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("io.reactivestax.dao")
public class AppConfig {

    //    //DB related
    @Bean
    public DataSource dataSource() {
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jdbc_demo");
        dataSource.setUsername("root"); // Replace with your DB username
        dataSource.setPassword("password"); // Replace with your DB password
        return dataSource;*/

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/spring_jdbc_demo");
        dataSource.setUsername("Dhruv.Desai"); // Replace with your DB username
        dataSource.setPassword("postgres"); // Replace with your DB password
        return dataSource;
    }

    //HikariCP if you need better connection pooling etc.
    /*@Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mydatabase");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        // Connection pooling settings
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(2);
        dataSource.setIdleTimeout(30000); // 30 seconds
        dataSource.setConnectionTimeout(20000); // 20 seconds
        return dataSource;
    }*/

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    //Service layer related

    //Controller layer related
}