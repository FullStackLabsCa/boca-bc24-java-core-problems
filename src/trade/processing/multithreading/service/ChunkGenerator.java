package trade.processing.multithreading.service;


import java.io.File;
import java.io.FileNotFoundException;

public interface ChunkGenerator {
    void generateAndSubmitAllChunks(File file) throws FileNotFoundException;
}

