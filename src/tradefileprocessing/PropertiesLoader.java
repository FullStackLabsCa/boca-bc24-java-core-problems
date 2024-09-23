package tradefileprocessing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("/Users/Abhay.Nimavat/IdeaProjects/StudentJavaCore/boca-bc24-java-core-problems/src/resources/application.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static void putProperty(String key, double threshold){
        properties.setProperty(key, String.valueOf(threshold));
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
