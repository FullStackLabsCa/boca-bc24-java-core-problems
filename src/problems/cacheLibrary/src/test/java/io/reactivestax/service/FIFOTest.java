package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FIFOTest {
    Cache<Integer, String> cacheMap ;

    @BeforeEach
    void setUp() {
        cacheMap = CacheFactory.createCache("FIFO",3);
    }

    @AfterEach
    void tearDown() {
        cacheMap.clearCache();
    }

    @Test
    void testEviction() {
        cacheMap.put(1, "Value 1");
        cacheMap.put(2, "Value 2");
        cacheMap.put(3, "Value 3");

        cacheMap.put(4, "Value 4");

        assertTrue(cacheMap.getAllKeys().contains(3), "Key 3 should still exist");
        assertFalse(cacheMap.getAllKeys().contains(1), "Key 1 should have been evicted");
        assertTrue(cacheMap.getAllKeys().contains(4), "Key 4 should have been added");
        assertTrue(cacheMap.getAllKeys().contains(2), "Key 2 should have been added");
    }

//    @Test
//    void testFifo() {
//        CacheFactory factory = new CacheFactory();
//
//        Cache<Integer, String> fifoCache = factory.createCache("FIFO", 3);
//
//        fifoCache.put(1, "One");
//        fifoCache.put(2, "Two");
//        fifoCache.put(3, "Three");
//        System.out.println("Initial FIFO Cache: " + fifoCache.getAllKeys());
//
//        fifoCache.put(4, "Four");
//        System.out.println("FIFO Cache after adding key 4: " + fifoCache.getAllKeys());
//
//        fifoCache.put(5, "Five");
//        System.out.println("FIFO Cache after adding key 5: " + fifoCache.getAllKeys());
//    }
}
