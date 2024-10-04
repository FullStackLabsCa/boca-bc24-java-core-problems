package multithreadingtrade;

import multithreadingtrade.services.ChunkProcessor;
import multithreadingtrade.services.CreateChunks;
import multithreadingtrade.services.TradeProcessorThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeRunner {


    public static void main(String[] args) {
        try {

            String filePath = "/Users/Gagandeep.Kaur/source/students/boca-bc24-java-core-problems/src/multithreadingtrade/utility/chunks/";
            String propertiesFilePath = "/Users/Gagandeep.Kaur/source/students/boca-bc24-java-core-problems/src/multithreadingtrade/utility/appilcation.properties"; // properties file path
            int numberOfChunks = CreateChunks.getNumberOfChunks(propertiesFilePath);

            CreateChunks chunkCreator = new CreateChunks();
            chunkCreator.generateChunks("/Users/Gagandeep.Kaur/source/students/boca-bc24-java-core-problems/src/multithreadingtrade/utility/trades.csv", numberOfChunks); // the input(trade) file path

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                if (!filePath.isEmpty()) {
                    executorService.submit(new ChunkProcessor(filePath + "trades" + i + ".csv"));
                }
            }
         //  executorService.shutdown();

            Thread.sleep(5000);

            TradeProcessorThreadPool tradeProcessorThreadPool = new TradeProcessorThreadPool();
            tradeProcessorThreadPool.putTradesIntoQueue(3);
          //  tradeProcessorThreadPool.shutDown();

        } catch (Exception e) {
            System.out.println("e.getMessage(MAIN) = " + e.getMessage());
        }
    }
}
