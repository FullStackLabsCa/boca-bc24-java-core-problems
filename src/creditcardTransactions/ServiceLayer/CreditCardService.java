package creditcardTransactions.ServiceLayer;

import creditcardTransactions.Model.CreditCardTransaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static creditcardTransactions.PresentationLayer.CreditCardTransactionProcessorRunner.creditCardTransactionQueue;

public class CreditCardService {


    // Step 1: Read transactions from a pipe-delimited file
    public static void readTransactionFileAndWriteToQueue(String filePath) {
        int counter=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if(!line.contains("Amount")) {
                    counter++;
                    String[] data = line.split("\\|");

                    CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                    System.out.println("adding transaction #" + counter + "in the queue >> " + creditCardTransaction);
                    // Thread.sleep(50);
                    creditCardTransactionQueue.putFirst(creditCardTransaction);  // Place transaction in the queue
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
