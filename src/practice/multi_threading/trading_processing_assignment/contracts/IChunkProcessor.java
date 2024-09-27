package practice.multi_threading.trading_processing_assignment.contracts;

import java.util.List;

public interface IChunkProcessor {
       void chunkProcessor(int numberOfChunks, List<String> listOfChunkFileName);
}
