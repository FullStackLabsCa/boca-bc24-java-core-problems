package problems.selfPractice.fileIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileCreation {

    public static void main(String[] args) {

        File file = new File("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/selfPractice/fileIO/example.txt");

        System.out.println("******Creating a File:******");
        try {

            if(file.createNewFile()){
                System.out.println("File ctreated: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("******Writing to a File: Using FileWriter******");
        try {
            FileWriter writer = new FileWriter("src/problems/selfPractice/fileIO/example.txt");
            writer.write("Hello, this is a sample text.");
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("******Writing to a File: Using BufferedWriter******");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/problems/selfPractice/fileIO/example.txt", true))) {
            writer.write("Appending this line to the file.");
            writer.newLine();  // Add a new line
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("******Reading from a File: Using BufferedReader******");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // path : file
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("******Reading from a File: Using Files class (from java.nio.file)******");

        try {
            List<String> allLines = Files.readAllLines(Paths.get(file.toURI()));
            for (String line : allLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("*****Checking If a File Exists*****");
        if (file.exists()) {
            System.out.println("File exists.");
        } else {
            System.out.println("File does not exist.");
        }

        /*System.out.println("*****Deleting a File*****");
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }*/

        System.out.println("*****Handling Directories : Creating a Directory*****");
        File directory = new File("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/selfPractice/fileIO/newDirectory");

        if (directory.mkdir()) {
            System.out.println("Directory created.");
        } else {
            System.out.println("Directory already exists or failed to create.");
        }

        System.out.println("*****Listing Files in a Directory*****");
        File directory1 = new File(".");
        String[] files = directory1.list();
        if (files != null) {
            for (String fileName : files) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }

        System.out.println("*****Advanced File Copying Using Files Class: java.nio.file*****");
        try {
            Path sourcePath = Paths.get(file.toURI());
            Path targetPath = Paths.get("src/problems/selfPractice/fileIO/target.txt");
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
