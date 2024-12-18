package io.reactivestax.utilities;

import io.reactivestax.exception.FileReadingRuntimeException;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

@Getter
public class Properties {
    private static Properties instance;

    private String readToFilepath;
    private String writeToFilepath;
    private String dbDriverClass;
    private String hibernateDialect;
    private String hibernateDBCreationMode;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    private Properties(String applicationPropertiesFileName) {
        loadApplicationProperties(applicationPropertiesFileName);
    }

    public static synchronized Properties getInstance(String applicationPropertiesFileName) {
        if (instance == null) {
            instance = new Properties(applicationPropertiesFileName);
        }
        return instance;
    }

    public static synchronized Properties getInstance() {
        if (instance == null) {
            instance = new Properties("application.properties");
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    public void loadApplicationProperties(String applicationPropertiesFileName) {
        java.util.Properties properties = new java.util.Properties();

        try (InputStream input = Properties.class.getClassLoader()
                .getResourceAsStream(applicationPropertiesFileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }

            properties.load(input);

            readToFilepath = properties.getProperty("readToFilePath");
            writeToFilepath = properties.getProperty("writeToFilePath");

            hibernateDialect = properties.getProperty("hibernate.dialect");
            hibernateDBCreationMode = properties.getProperty("hibernate.hbm2ddl.auto");
            dbDriverClass = properties.getProperty("db.driver.class");

            dbUrl = properties.getProperty("dbUrl");
            dbUsername = properties.getProperty("dbUsername");
            dbPassword = properties.getProperty("dbPassword");

        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found.");
        }
    }
}
