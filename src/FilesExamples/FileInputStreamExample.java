package FilesExamples;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("binaryfile.dat")) {
            int byteContent;
            while ((byteContent = fis.read()) != -1) {
                System.out.print((char) byteContent);  // Convert byte to character (for demonstration)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
