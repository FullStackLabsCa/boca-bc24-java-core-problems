package trade_processing_multithreading;


import java.io.File;
import java.io.FileNotFoundException;

public interface ChunkProcessor {
    boolean processChunk(File file) throws FileNotFoundException;
}
