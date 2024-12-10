package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.Test;

class FifoTest {
    @Test
    void testFifo() {
        CacheFactory factory = new CacheFactory();

        Cache<Integer, String> fifoCache = factory.createCache("FIFO", 3);

        fifoCache.put(1, "One");
        fifoCache.put(2, "Two");
        fifoCache.put(3, "Three");
        System.out.println("Initial FIFO Cache: " + fifoCache.getAllKeys());

        fifoCache.put(4, "Four"); // Adding this will evict the first inserted key (key 1)
        System.out.println("FIFO Cache after adding key 4: " + fifoCache.getAllKeys());

        fifoCache.put(5, "Five"); // Adding this will evict the next oldest key (key 2)
        System.out.println("FIFO Cache after adding key 5: " + fifoCache.getAllKeys());
    }
}
