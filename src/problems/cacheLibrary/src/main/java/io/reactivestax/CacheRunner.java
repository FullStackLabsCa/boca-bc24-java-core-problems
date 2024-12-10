package io.reactivestax;

import io.reactivestax.factory.CacheFactory;
import io.reactivestax.service.Cache;

public class CacheRunner {
    public static void main(String[] args) throws InterruptedException {
        CacheFactory factory = new CacheFactory();

        System.out.println("***** TTL Example *****");
        Cache<String, String> ttlCache = factory.createCache("TTL");
        ttlCache.put("Key1", "Value1", 2);
        ttlCache.put("Key2", "Value2", 5);

        System.out.println("Initial TTL Cache Size: " + ttlCache.getSizeofCache());

        Thread.sleep(3000);

        System.out.println("Value for key 'Key1': " + ttlCache.getById("Key1"));
        System.out.println("Value for key 'Key2': " + ttlCache.getById("Key2").value);
        System.out.println("TTL Cache Size after 3 seconds: " + ttlCache.getSizeofCache());
    }
}
