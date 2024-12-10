package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RRTest {
    Cache<Integer, String> cacheMap ;

    @BeforeEach
    void setUp() {
        cacheMap = CacheFactory.createCache("RR",3);
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

        Set<Integer> allKeys1 = cacheMap.getAllKeys();

        cacheMap.put(5, "Value 5");

        Set<Integer> allKeys2 = cacheMap.getAllKeys();

        assertNotEquals(allKeys2.toArray(), allKeys1.toArray());
    }

//    @Test
//    void testRR() {
//        CacheFactory factory = new CacheFactory();
//
//        System.out.println("***** RR Example *****");
//        Cache<Integer, String> rrCache = factory.createCache("RR", 3);
//
//        rrCache.put(1, "One");
//        rrCache.put(2, "Two");
//        rrCache.put(3, "Three");
//
//        System.out.println("Initial LRU Cache: " + rrCache.getAllKeys());
//
//        rrCache.put(4, "Four");
//        System.out.println("LRU Cache after adding key 4: " + rrCache.getAllKeys());
//
//        rrCache.put(5, "Five");
//        System.out.println("LRU Cache after adding key 5: " + rrCache.getAllKeys());
//    }
}
