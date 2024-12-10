package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LFUTest {
    Cache<Integer, String> cacheMap ;

    @BeforeEach
    void setUp() {
        cacheMap = CacheFactory.createCache("LFU",3);
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

        cacheMap.getById(1);
        cacheMap.getById(3);

        cacheMap.put(4, "Value 4");

        assertTrue(cacheMap.getAllKeys().contains(3), "Key 3 should still exist");
        assertTrue(cacheMap.getAllKeys().contains(1), "Key 1 should still exist");
        assertTrue(cacheMap.getAllKeys().contains(4), "Key 4 should have been added");
        assertFalse(cacheMap.getAllKeys().contains(2), "Key 2 should have been evicted");
    }

//    @Test
//    void testLFU() {
//        CacheFactory factory = new CacheFactory();
//
//        System.out.println("***** LFU Example *****");
//        Cache<Integer, String> lfuCache = factory.createCache("LFU", 3);
//
//        lfuCache.put(1, "One");
//        lfuCache.put(2, "Two");
//        lfuCache.put(3, "Three");
//
//        lfuCache.getById(1);
//        lfuCache.getById(3);
//
//        System.out.println("Initial LRU Cache: " + lfuCache.getAllKeys());
//
//        lfuCache.put(4, "Four");
//        System.out.println("LRU Cache after adding key 4: " + lfuCache.getAllKeys());
//
//        lfuCache.put(5, "Five");
//        System.out.println("LRU Cache after adding key 5: " + lfuCache.getAllKeys());
//    }
}
