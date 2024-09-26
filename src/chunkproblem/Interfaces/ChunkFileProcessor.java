package chunkproblem.Interfaces;

public interface ChunkFileProcessor {
    void readCSVFile();
    void creatingChunkFile(String csvFilePath, int chunkCount);
}
