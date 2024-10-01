package io.reacticestax.tradeprocessingmultithreadingassignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {

        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {

        return Integer.parseInt(properties.getProperty(key));
    }


}