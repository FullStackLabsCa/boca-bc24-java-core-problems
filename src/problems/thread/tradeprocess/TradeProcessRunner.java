package thread.tradeprocess;

import thread.tradeprocess.service.TradeService;
import thread.tradeprocess.exceptions.InvalidNumberOfChunkValueRuntimeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TradeProcessRunner {
    public static void main(String[] args) {
        int numberOfChunks;
        Properties properties = new Properties();

//        Scanner scanner = new Scanner(System.in);
//        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/thread/tradeprocess/resources/application.properties"))) {
            properties.load(reader);

            numberOfChunks = Integer.parseInt(properties.getProperty("numberOfChunks"));
            if (numberOfChunks < 1) {
                throw new InvalidNumberOfChunkValueRuntimeException("Please fix the application properties it should be integer and > 1");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input. Please try again with a valid decimal value.", e);
        }

        TradeService.setupDbConnectionAndReadFile("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/src/problems/thread/tradeprocess/trades1.csv", numberOfChunks);
    }
}
