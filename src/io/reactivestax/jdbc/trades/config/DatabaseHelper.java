package io.reactivestax.jdbc.trades.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DatabaseHelper {

    private DatabaseHelper(){}

    private static final Logger log = LoggerFactory.getLogger(DatabaseHelper.class);

    public static HikariDataSource getConnection(String portNumber) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:" + portNumber + "/bootcamp");
        config.setUsername(readThresholdLimitFromProperties("user.root"));
        config.setPassword(readThresholdLimitFromProperties("user.password"));
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        return new HikariDataSource(config);
    }

    public static String readThresholdLimitFromProperties(String value) {
        try(FileReader reader = new FileReader("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/application.properties")) {

            Properties properties = new Properties();
            properties.load(reader);
            value = String.valueOf(properties.get(value));
        } catch (IOException e) {
            log.error("Error: ", e);
        }
        return value;
    }

}
