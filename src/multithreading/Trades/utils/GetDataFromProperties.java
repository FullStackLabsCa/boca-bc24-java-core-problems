package multithreading.Trades.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromProperties {
    public String readThresholdLimitFromProperties(String filePath, String valueOf) {
        String value = null;
        try {
            FileReader reader = new FileReader(filePath);
            Properties properties = new Properties();
            properties.load(reader);
            value = (String) properties.get(valueOf);
        } catch (IOException e) {
            System.out.println(e);
        }
        return value;
    }
}
