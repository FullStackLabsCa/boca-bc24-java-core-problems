package TradeProcessingMultiThreadingAssignment.Service;
import java.io.*;

public interface ChunkGeneratorInterface {



    int fileLineCounter() throws IOException;

    void chunkFileGenerator() throws IOException;
}
