package problems.jdbc.trade.validator;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathValidator {

    // Method to validate the file path
    public static boolean isValidPath(String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            // Check if the path exists and is either a file or directory
            return Files.exists(path);
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
            return false;  // Invalid path string format
        }
    }
}
