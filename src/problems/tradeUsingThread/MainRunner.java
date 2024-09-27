package problems.tradeUsingThread;

import problems.tradeUsingThread.fileReader.ChunkGenerator;
import problems.tradeUsingThread.processor.ChunkProcessor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunner {
    public static void main(String[] args) {
        ChunkGenerator chunkGenerator = new ChunkGenerator("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/mainFile/trades.csv");
        ExecutorService singleThreadEexecutorService = Executors.newSingleThreadExecutor();
        singleThreadEexecutorService.submit(chunkGenerator);
        singleThreadEexecutorService.shutdown();


        List<String> chunkFiles = ChunkGenerator.chunkGenerator();
        System.out.println(chunkFiles);

        ExecutorService chunkProcessorEexecutorService = Executors.newFixedThreadPool(10);
        for (String chunkFile : chunkFiles) {
            chunkProcessorEexecutorService.submit(() -> {
                ChunkProcessor chunkProcessor = new ChunkProcessor("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/tradeFileChunks/" + chunkFile);
                chunkProcessor.run();
            });
        }
        chunkProcessorEexecutorService.shutdown();
    }
}
