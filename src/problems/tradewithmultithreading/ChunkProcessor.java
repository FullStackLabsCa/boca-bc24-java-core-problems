package problems.tradewithmultithreading;

public interface ChunkProcessor {
   void writeToTradesPayloadTable(String fileChunks);
   int consultConcurrentHashmapTable(String accountNumber);
   void writesToQueue(int QueueID);

}
