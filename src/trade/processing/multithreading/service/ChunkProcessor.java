package trade.processing.multithreading.service;


import java.io.File;
import java.io.FileNotFoundException;

public interface ChunkProcessor {
    void processChunk(File file) throws FileNotFoundException;
}
