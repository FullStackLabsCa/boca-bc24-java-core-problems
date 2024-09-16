package problems.jdbc.optimisticlocking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;


public class FileProcessor {


  // Read transactions from a pipe-delimited file
    public static ArrayBlockingQueue<CreditCardTransaction> readTransactionFileAndWriteToQueue(String filePath, ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction #"+counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the queue
            }
            return creditCardTransactionQueue;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return creditCardTransactionQueue;
    }
}
