package practice.file_io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CreditCardTransactionParser {
    public static void main(String[] args) {
        File file = new File("/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/example.txt");

        try (Scanner scanner = new Scanner(new FileReader(file))) {
            // Set the delimiter to the pipe symbol (|) and newline
            Scanner scannerDelimiter = scanner.useDelimiter("\\||\n");

            while (scannerDelimiter.hasNext()) {
                String cardNumber = scannerDelimiter.next();
//                String string = scannerDelimiter.nextLine();
                String cardType = scannerDelimiter.next();
                String transactionType = scannerDelimiter.next();
                double amount = scannerDelimiter.nextDouble();
                double balance = scannerDelimiter.nextDouble();

                // Display the parsed transaction details
                System.out.println("Card Number: " + cardNumber);
                System.out.println("Card Type: " + cardType);
                System.out.println("Transaction Type: " + transactionType);
                System.out.println("Amount: $" + amount);
                System.out.println("Balance: $" + balance);
                System.out.println("-------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}