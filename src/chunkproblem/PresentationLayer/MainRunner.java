package chunkproblem.PresentationLayer;

import chunkproblem.Interfaces.ChunkFileProcessor;
import chunkproblem.ServiceLayer.ChunkFileGenerator;

public class MainRunner {
    public static void main(String[] args) {
        ChunkFileProcessor chunkFileProcessor = new ChunkFileGenerator();
        chunkFileProcessor.readCSVFile();

      //  ChunkProcessorToThread chunkProcessorToThread = new ProcessingChunksToThread();

    }
}
