package problems.thread.trade;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThreadTradeRunner {
    public static void main(String[] args) throws FileNotFoundException {
        // read file and load data into the list
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a file name >>>");
        String fileName = scanner.next().trim();
        ThreadTradeFileReader tradeFileReader = new ThreadTradeFileReader();
        tradeFileReader.fileReader(fileName);

        //generating chunk files
        ChunkFileGenerator.chunkGenerator(ThreadTradeService.filePath);
    }
}
