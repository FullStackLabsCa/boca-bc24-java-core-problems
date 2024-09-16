package transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;

public class FileReader {

    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionDeque = new LinkedBlockingDeque<>();

    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction #"+counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionDeque.put(creditCardTransaction);  // Place transaction in the queue
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
