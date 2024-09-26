package TradeProcessingMultiThreadingAssignment.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeChunkProcessor implements Runnable {

    TradeChunkGeneratorService service = new TradeChunkGeneratorService();
     int chunkCount= 10;
     String baseChunkFilePath= "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/TradeProcessingMultiThreadingAssignment/chunks/file_chunk_";
    public void submitChunkToThreadPool() {


        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            for(int i = 1 ; i <= chunkCount ; i++) {
//                Runnable task = () -> executor.submit(new StringBuilder().append(baseChunkFilePath).append(i).append(".csv").toString());

            }
        }catch(Exception e){
            executor.shutdown();
            System.out.println("error while submitting task to chunkProcessor threadPool");

        }
    }

    @Override
    public void run() {

    }
}
