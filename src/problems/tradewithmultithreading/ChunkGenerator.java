package problems.tradewithmultithreading;

import java.util.List;

public interface ChunkGenerator {
    List<String> readsFileAndGenerateChunks(String filePath);
}

