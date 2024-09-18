package practice.file_io;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class FilesReadExample {
    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("example.csv"));

            allLines.stream().forEach(System.out::println);

//            for (String line : allLines) {
//                System.out.println(" entry is : " + line);
//            }


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}