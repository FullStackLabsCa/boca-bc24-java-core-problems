package problems.selfPractice.fileIO;

import java.io.*;

public class FileCreation2 {

    public static void main(String[] args) {

        File file = new File("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/selfPractice/fileIO/binaryfile.dat");

        System.out.println("1. Byte Streams: Byte streams use the InputStream and OutputStream classes.\n - These streams handle data as bytes (8-bit units).\n - Used for handling raw binary data, suitable for all file types including images, audio, and video files.\n - The most commonly used byte stream classes are:");
        System.out.println("----------------------------------------------------------------");
        System.out.println("*****FileOutputStream Example - write binary data ");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            String content = "This is binary content.";
            byte[] bytes = content.getBytes();
            fos.write(bytes);
            System.out.println("Data written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("*****FileInputStream Example - ead binary data");
        try (FileInputStream fis = new FileInputStream(file)) {
            int byteContent;
            while ((byteContent = fis.read()) != -1) {
                System.out.print((char) byteContent);  // Convert byte to character (for demonstration)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        File file2 = new File("/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/selfPractice/fileIO/example2.txt");

        System.out.println();
        System.out.println("\n2. Character Streams : \n - Character streams use the Reader and Writer classes to read and write text files. \n - They handle data as 16-bit Unicode characters, making them ideal for text files. \n - Specifically designed for handling character data, making them ideal for text files.");
        System.out.println("----------------------------------------------------------------");
        System.out.println("*****FileWriter*****");

        try (FileWriter writer = new FileWriter(file2)) {
            writer.write("This is some text content.");
            System.out.println("Text written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("*****FileReader*****");
        try (FileReader reader = new FileReader(file2)) {
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("\n3. Buffered Streams \n - Buffered streams (BufferedInputStream, BufferedOutputStream, BufferedReader, and BufferedWriter) \n - add an internal buffer to improve read/write performance by reducing the number of I/O operations.");
        System.out.println("----------------------------------------------------------------");

        System.out.println("*****BufferedOutputStream******");
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("binaryfile.dat"))) {
            String content = "Buffered binary content.";
            byte[] bytes = content.getBytes();
            bos.write(bytes);
            System.out.println("Data written to the file with buffering.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("*****BufferedInputStream *****");
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("binaryfile.dat"))) {
            int byteContent;
            while ((byteContent = bis.read()) != -1) {
                System.out.print((char) byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
