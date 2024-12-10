package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.Test;

class LruTest {

    @Test
    void testLRU() {
        CacheFactory factory = new CacheFactory();

        System.out.println("***** LRU Example *****");
        Cache<Integer, String> lruCache = factory.createCache("LRU", 3);

        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");

        lruCache.getById(1);
        lruCache.getById(3);

        System.out.println("Initial LRU Cache: " + lruCache.getAllKeys());

        lruCache.put(4, "Four");
        System.out.println("LRU Cache after adding key 4: " + lruCache.getAllKeys());

        lruCache.put(5, "Five");
        System.out.println("LRU Cache after adding key 5: " + lruCache.getAllKeys());
    }
}
