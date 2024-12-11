package io.reactivestax.cachelibraryv2;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;
    private final Map<K, CacheEntry<K, V>> cacheEntryMap;

    public LRUEvictionPolicy(int capacity) {
        this.capacity = capacity;
        this.cacheEntryMap = new LinkedHashMap<>();
    }

    @Override
    public void evict(Map<K, CacheEntry<K, V>> cacheMap) {
        if (cacheMap.size() >= capacity) {
            K leastRecentlyUsedKey = null;
            long oldestAccessTime = Long.MAX_VALUE;
            for (Map.Entry<K, CacheEntry<K, V>> entry : cacheMap.entrySet()) {
                if (entry.getValue().getLastAccessedTime() < oldestAccessTime) {
                    oldestAccessTime = entry.getValue().getLastAccessedTime();
                    leastRecentlyUsedKey = entry.getKey();
                }
            }
            if (leastRecentlyUsedKey != null) {
                cacheMap.remove(leastRecentlyUsedKey);
            }
        }
    }
}