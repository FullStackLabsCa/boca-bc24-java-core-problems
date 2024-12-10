package io.reactivestax.policy;

import io.reactivestax.service.Cache;

public class TTL<K, V> implements EvictionPolicy<K, V> {

    @Override
    public void eviction(Cache<K, V> cache) {
        /*while (true) {
            try {
                for (K key : cache.getAllKeys()) {
                    CacheEntryValue<V> entry = cache.getById(key);
                    if (entry != null && entry.isExpired()) {
                        cache.removeById(key);
                        System.out.println("Removed expired key: " + key);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("timeToLeave policy interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }*/
    }
}
