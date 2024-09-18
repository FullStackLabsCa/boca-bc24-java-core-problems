package practice.file_io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

public class FileCopyExample {
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