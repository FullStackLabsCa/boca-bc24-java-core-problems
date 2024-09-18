package practice.file_io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.csv", true))) {
            writer.newLine();
            writer.write("Appending this line to the file.");
            writer.newLine();  // Add a new line

            for (int i = 0; i < 10000; i++) { //data is stored in a buffer and written in larger chunks, making it much faster.
                writer.write("This is line, " + i + "\n");

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}