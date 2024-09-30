package trade_processing_multithreading;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface ChunkGenerator {
    boolean generateAndSubmitAllChunks(File file) throws FileNotFoundException;
}

