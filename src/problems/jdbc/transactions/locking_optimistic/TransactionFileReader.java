package problems.jdbc.transactions.locking_optimistic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import static problems.jdbc.transactions.locking_optimistic.TransactionProcessor.creditCardTransactionQueue;

public class TransactionFileReader {
    public static void readTransactionFileAndWriteToQueue(String filePath, ArrayBlockingQueue<CreditCardTransaction> transactionQueue) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            String line = reader.readLine();  // This reads the header line and ignores it

            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction #"+counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the queue
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
