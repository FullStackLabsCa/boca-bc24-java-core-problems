package problems.trading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.LinkedBlockingDeque;

public class FileProcessor {
    private static int counter = 0;
    private static int errorLengthInQueue = 0;
    private static int errorThreshold = 25;
    private static boolean isValid = false;

    public static LinkedBlockingDeque<TradeTransaction> readTransactionFileAndWriteToQueue(String filePath, LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] row = line.strip().split(",");

                isValid = validateCSVFile(row);

                if (isValid) {
                    TradeTransaction tradeTransaction = new TradeTransaction(row[0], row[1], Integer.parseInt(row[2]), Double.parseDouble(row[3]), Date.valueOf(row[4]));
                    System.out.println("adding transaction #" + counter + " in the queue >> " + tradeTransaction);
                    tradingTransactionDeQueue.put(tradeTransaction);
                }
            }
            if (errorLengthInQueue > errorThreshold) {
                throw new HitErrorsThresholdException("Errors exceeded threshold limit");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return tradingTransactionDeQueue;
    }

    private static boolean validateCSVFile(String[] data){
        isValid = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null || data[i].trim().isEmpty() || data[i].contains("INVALID")) {
                errorLengthInQueue++;
                 isValid = false;
                System.out.println("error in the line >>" + counter);
                return isValid;
            }
            break;
        }

        try(data[2] = Integer.n)

        return isValid;
    }
}
