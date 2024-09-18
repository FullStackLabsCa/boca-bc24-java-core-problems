package practice.file_io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerDataTypesExample {
    public static void main(String[] args) {
        File file = new File("numbers.txt");
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            // Read integers from the file
            while (scanner.hasNext()) {
                try {
                    int number = scanner.nextInt();
                    System.out.println("Read number: " + number);
                } catch (InputMismatchException e) {
                    System.out.println("***** Invalid numbers: " + scanner.next() + " ******");
                }
            }
        } catch (IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}