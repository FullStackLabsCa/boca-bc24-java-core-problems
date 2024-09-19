package FilesExamples;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class FilesReadExample {
    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("example.txt"));
            for (String line : allLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
