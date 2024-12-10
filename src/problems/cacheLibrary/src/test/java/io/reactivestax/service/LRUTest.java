package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LRUTest {
    Cache<Integer, String> cacheMap ;

    @BeforeEach
    void setUp() {
        cacheMap = CacheFactory.createCache("LRU",3);
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
//    void testLRU() {
//        CacheFactory factory = new CacheFactory();
//
//        System.out.println("***** LRU Example *****");
//        Cache<Integer, String> lruCache = factory.createCache("LRU", 3);
//
//        lruCache.put(1, "One");
//        lruCache.put(2, "Two");
//        lruCache.put(3, "Three");
//
//        lruCache.getById(1);
//        lruCache.getById(3);
//
//        System.out.println("Initial LRU Cache: " + lruCache.getAllKeys());
//
//        lruCache.put(4, "Four");
//        System.out.println("LRU Cache after adding key 4: " + lruCache.getAllKeys());
//
//        lruCache.put(5, "Five");
//        System.out.println("LRU Cache after adding key 5: " + lruCache.getAllKeys());
//    }
}
