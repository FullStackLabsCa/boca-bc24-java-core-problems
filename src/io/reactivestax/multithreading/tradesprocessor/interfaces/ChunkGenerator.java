package io.reactivestax.multithreading.tradesprocessor.interfaces;

public interface ChunkGenerator {
    void generateChunks(String filePath, int numberOfChunks);
}

