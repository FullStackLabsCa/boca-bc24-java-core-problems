package TradeProcessingMultiThreadingAssignment.Service;
import java.io.*;

public interface ChunkGeneratorInterface {

    void setFilePath(String filePath);
    void readTradeFile() throws FileNotFoundException;
    void generateChunk();


}
