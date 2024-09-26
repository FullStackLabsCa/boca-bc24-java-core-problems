package TradeProcessingMultiThreadingAssignment.Service.newpackage;

import java.io.File;
import java.io.FileNotFoundException;

public interface ChunkGenerator {
    boolean generateNextChunkAndSubmit(File file) throws FileNotFoundException;
}


