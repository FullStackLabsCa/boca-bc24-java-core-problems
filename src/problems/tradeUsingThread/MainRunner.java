package problems.tradeUsingThread;

import problems.tradeUsingThread.fileReader.ChunkGenerator;
import problems.tradeUsingThread.processor.ChunkProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunner {
    public static void main(String[] args) throws IOException {
        ChunkGenerator chunkGenerator= new ChunkGenerator("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/mainFile/trades.csv");
        ExecutorService singleThreadEexecutorService = Executors.newSingleThreadExecutor();
        singleThreadEexecutorService.execute(chunkGenerator);
        singleThreadEexecutorService.shutdown();

        while (!singleThreadEexecutorService.isTerminated()) {

        }

        List<String> chunkFiles = ChunkGenerator.fileList;

        ExecutorService chunkProcessorEexecutorService = Executors.newFixedThreadPool(10);
        for (String chunkFile : chunkFiles) {
            ChunkProcessor chunkProcessor = new ChunkProcessor("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/tradeFileChunks/" + chunkFile);
            chunkProcessorEexecutorService.execute(chunkProcessor);
        }
        chunkProcessorEexecutorService.shutdown();
    }
}
