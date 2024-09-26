package chunkproblem.ServiceLayer;

import chunkproblem.Interfaces.ChunkProcessorToThread;

public class ProcessingChunksToThread implements ChunkProcessorToThread {

    public void processChunksToThread(String outputDirectory, int totalChunks) {
        for (int i = 1; i <= totalChunks; i++) {
            final int chunkNumber = i;
            String chunkFilePath = outputDirectory + "chunk_" + chunkNumber + ".csv";

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Processing chunk: " + chunkFilePath);
                    try {
                        Thread.sleep(5000); // Simulating processing time
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Finished processing chunk: " + chunkFilePath);
                }
            };
            task.run();
        }
        System.out.println("All chunks processed.");
    }

}
