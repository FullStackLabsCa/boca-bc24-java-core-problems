package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.Test;

class LfuTest {
    @Test
    void testLRU() {
        CacheFactory factory = new CacheFactory();

        System.out.println("***** LFU Example *****");
        Cache<Integer, String> lfuCache = factory.createCache("LFU", 3);

        lfuCache.put(1, "One");
        lfuCache.put(2, "Two");
        lfuCache.put(3, "Three");

        lfuCache.getById(1);
        lfuCache.getById(3);

        System.out.println("Initial LRU Cache: " + lfuCache.getAllKeys());

        lfuCache.put(4, "Four");
        System.out.println("LRU Cache after adding key 4: " + lfuCache.getAllKeys());

        lfuCache.put(5, "Five");
        System.out.println("LRU Cache after adding key 5: " + lfuCache.getAllKeys());
    }
}
