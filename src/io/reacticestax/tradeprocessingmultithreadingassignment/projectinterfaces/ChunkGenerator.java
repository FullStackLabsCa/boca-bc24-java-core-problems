package io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ChunkGenerator {

    int countLines(File file) throws FileNotFoundException;

    void generateChunkAndSubmit(File file) throws IOException;

    // boolean generateNextChunkAndSubmit(File file) throws FileNotFoundException;
}


