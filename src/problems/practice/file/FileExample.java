package problems.practice.file;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileExample {
    public static void main(String[] args) {
        try {
            Path sourcePath = Paths.get("source.txt");
            Path targetPath = Paths.get("target.txt");
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
