package problems.tradeUsingThread.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ChunkProcessor implements Runnable{
    static String filePath;

    public ChunkProcessor(String filePath) {
        this.filePath= filePath;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is processing file: " + filePath);


            readChunk();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readChunk() throws FileNotFoundException {
        try (Scanner scanner= new Scanner(new FileReader(filePath))){
            scanner.useDelimiter("\n|,");

//            while (scanner.hasNext()){
//                String data = scanner.next();
//                System.out.println("Processing data: " + data);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
