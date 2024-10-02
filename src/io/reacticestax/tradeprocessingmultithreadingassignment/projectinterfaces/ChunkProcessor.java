package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

import java.io.File;
import java.io.IOException;

public interface ChunkProcessor {
    void processChunk(String chunkFilePath) throws IOException;


}
