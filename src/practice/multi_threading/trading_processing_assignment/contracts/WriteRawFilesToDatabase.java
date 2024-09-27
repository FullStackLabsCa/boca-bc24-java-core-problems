package practice.multi_threading.trading_processing_assignment.contracts;

import java.util.List;

public interface WriteRawFilesToDatabase {
       void writeRawFileToDatabase(int numberOfChunks, List<String> listOfChunkFileName);
}
