package trading_processing;

import java.io.*;
import java.util.Scanner;

public class TradeFileReader {

    static File file = new File("trade_data.csv");

    public static int countEntries() {
        int totalEntries = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(file));) {
            while (scanner.hasNext()) {
                totalEntries++;
                scanner.next();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return totalEntries;
    }
}