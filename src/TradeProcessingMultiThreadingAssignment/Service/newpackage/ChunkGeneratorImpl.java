package TradeProcessingMultiThreadingAssignment.Service.newpackage;

import java.io.File;
import java.io.FileNotFoundException;

public class ChunkGeneratorImpl implements ChunkGenerator{
    ChunkProcessor chunkProcessor;
    @Override
    public boolean generateNextChunkAndSubmit(File file) throws FileNotFoundException {
        while(true){
            //calculate file lines
            //split the chunk
            //submit the chunk
            chunkProcessor.submitChunk(file);
            //next chunk
        }
//        return false;
    }
}
