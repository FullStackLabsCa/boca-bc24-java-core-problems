package chunkproblem.Interfaces;

public interface ChunkProcessorToThread {
    void processChunksToThread(String outputDirectory, int totalChunks);
}

//chunk generat)or will feed the file name to the task being
// submitted to the chunk Processor threadPool (threadPool size
// will be equal to the numberOfChunks
