package problems.creditcard.util;


import problems.creditcard.entity.CreditCardTransaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TransactionFileReader {

private static BlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);
    public static BlockingDeque<CreditCardTransaction> readTransactionFileAndWriteToQueue(String filePath) {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split("\\|");
                CreditCardTransaction creditCardTransaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                System.out.println("adding transaction #" + counter + "in the queue >> " + creditCardTransaction);
//                Thread.sleep(100);
                creditCardTransactionQueue.add(creditCardTransaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return creditCardTransactionQueue;
    }
}
