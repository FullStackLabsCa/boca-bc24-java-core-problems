package practice.file_io;

import java.io.File;
import java.io.InputStream;

public class ListFilesExample {
    public static void main(String[] args) {
        File directory = new File(".");
        String[] files = directory.list();
        if (files != null) {
            for (String fileName : files) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }
}