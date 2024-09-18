package problems.trading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.LinkedBlockingDeque;

public class FileProcessor {
    public static LinkedBlockingDeque<TradeTransaction> readTransactionFileAndWriteToQueue(String filePath, LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.strip().split(",");
                TradeTransaction tradeTransaction = new TradeTransaction(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), Date.valueOf(data[4]));
                System.out.println("adding transaction #" + counter + " in the queue >> " + tradeTransaction);
                tradingTransactionDeQueue.put(tradeTransaction);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return tradingTransactionDeQueue;
    }
}
