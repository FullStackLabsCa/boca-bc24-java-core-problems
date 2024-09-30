package io.reactivestax.multithreading.tradesprocessor.interfaces;

public interface ChunkProcessor {
    Runnable processChunk(int chunk);
}
