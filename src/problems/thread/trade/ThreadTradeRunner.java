package problems.thread.trade;


import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("java:S106")
public class ThreadTradeRunner {
    public static void main(String[] args) throws FileNotFoundException {
        // read file and load data into the list
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please enter a file name >>>");
//        String fileName = scanner.next().trim();
//        ThreadTradeFileReader tradeFileReader = new ThreadTradeFileReader();
//        tradeFileReader.fileReader(fileName);

        //generating chunk files
//        new ChunkFileGenerator();

        //Processing generated Chunk
//        ChunkFileProcessor.chunkFileProcessor();

        // creating chunkProcessor ThreadPool
        int numOfThreadForChunkPool = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreadForChunkPool);
        for (int i = 0; i < numOfThreadForChunkPool; i++) {
            String fileName = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/src/problems/thread/trade/rawdata/trade_chunk_" + (i + 1) + ".csv";
            executorService.submit(new ChunkFileProcessor(fileName));
        }
        executorService.shutdown();
        System.out.println(ThreadTradeService.ANSI_GREEN + ">>>>>>>>>>Data inserted in the trade payloads table<<<<<<<<<<");
    }
}
