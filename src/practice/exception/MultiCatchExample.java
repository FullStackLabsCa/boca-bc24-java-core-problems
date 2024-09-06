package practice.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiCatchExample {
    public static void main(String[] args) {
        try {
            // Attempt to read from a file and parse the content
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line = reader.readLine();
            int number = Integer.parseInt(line);
            System.out.println("Parsed number: " + number);
        } catch (IOException | NumberFormatException ex) {
            // Handle both IOException and NumberFormatException
            System.out.println("An error occurred: " + ex.getMessage());

            // The following line would cause a compilation error because 'ex' is final
            // ex = new Exception("Another exception"); // Uncommenting this line will cause a compilation error
        } catch (RuntimeException runtimeException) {
            System.out.println("Hello RuntimeException,,,,");
        }finally {
            System.out.println("Hello Finally Block");
        }

    }
}
