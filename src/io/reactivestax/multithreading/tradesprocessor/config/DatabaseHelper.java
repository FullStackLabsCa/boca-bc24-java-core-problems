package io.reactivestax.multithreading.tradesprocessor.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.multithreading.tradesprocessor.utils.GetDataFromProperties;


public class DatabaseHelper {

    private DatabaseHelper(){}

    public static HikariDataSource getConnection(String portNumber) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:" + portNumber + "/bootcamp");
        GetDataFromProperties getDataFromProperties = new GetDataFromProperties();
        String propertiesFile = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/io/reactivestax/multithreading/tradesprocessor/resources/ data.properties";
        config.setUsername(getDataFromProperties.readValueFromPropertiesFile(propertiesFile,"user.root"));
        config.setPassword(getDataFromProperties.readValueFromPropertiesFile(propertiesFile,"user.password"));
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        return new HikariDataSource(config);
    }

}
