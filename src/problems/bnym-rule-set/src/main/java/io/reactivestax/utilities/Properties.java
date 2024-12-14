package io.reactivestax.utilities;

import io.reactivestax.exception.FileReadingRuntimeException;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

@Getter
public class Properties {
    private static Properties instance;

    private String filepath;

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

            filepath = properties.getProperty("filePath");

        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found.");
        }
    }
}
