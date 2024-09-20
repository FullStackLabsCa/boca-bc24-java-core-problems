package problems.tradeFileParser.reader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import static problems.tradeFileParser.MainRunner.threshold;

public class ThresholdReaderImplementation implements ThresholdReader {

    @Override
    public double readThreshold() {
        Properties properties = new Properties();
        double threshold = 0.0;
        try (FileInputStream input = new FileInputStream("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeFileParser/application.properties")) {
            properties.load(input);
            threshold = Double.parseDouble(properties.getProperty("error.threshold"));
        } catch (IOException e) {
            System.out.println("Error reading the properties file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the threshold value: " + e.getMessage());
        }
        return threshold;
    }
}
