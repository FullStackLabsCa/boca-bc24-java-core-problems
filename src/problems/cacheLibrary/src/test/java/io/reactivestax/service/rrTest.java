package io.reactivestax.service;

import io.reactivestax.factory.CacheFactory;
import org.junit.jupiter.api.Test;

class rrTest {
    @Test
    void testRR() {
        CacheFactory factory = new CacheFactory();

        System.out.println("***** RR Example *****");
        Cache<Integer, String> rrCache = factory.createCache("RR", 3);

        rrCache.put(1, "One");
        rrCache.put(2, "Two");
        rrCache.put(3, "Three");

        rrCache.getById(1);
        rrCache.getById(3);

        System.out.println("Initial LRU Cache: " + rrCache.getAllKeys());

        rrCache.put(4, "Four");
        System.out.println("LRU Cache after adding key 4: " + rrCache.getAllKeys());

        rrCache.put(5, "Five");
        System.out.println("LRU Cache after adding key 5: " + rrCache.getAllKeys());
    }
}
