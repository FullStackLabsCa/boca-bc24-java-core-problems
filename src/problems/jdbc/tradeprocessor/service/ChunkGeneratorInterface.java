package problems.jdbc.tradeprocessor.service;

import java.io.IOException;

public interface ChunkGeneratorInterface {
    void chunkGenerator(long numOfLines, String path) throws IOException;
}
