package practice.file_io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ScannerDelimiterExample {
    public static void main(String[] args) {
        File file = new File("example.csv");

        try (Scanner scanner = new Scanner(new FileReader(file))) {
            // Use comma as the delimiter
            scanner.useDelimiter(",");

            while (scanner.hasNext()) {
                String data = scanner.next();
                System.out.println("Data: " + data);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}