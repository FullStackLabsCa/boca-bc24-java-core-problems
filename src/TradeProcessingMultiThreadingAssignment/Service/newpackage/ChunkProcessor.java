package TradeProcessingMultiThreadingAssignment.Service.newpackage;

import java.io.File;
import java.io.FileNotFoundException;

public interface ChunkProcessor {

    void submitChunk(File file) throws FileNotFoundException;


}
