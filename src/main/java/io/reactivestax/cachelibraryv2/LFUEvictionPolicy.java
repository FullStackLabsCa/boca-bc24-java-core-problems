package io.reactivestax.cachelibraryv2;

import java.util.Iterator;
import java.util.Map;

public class LFUEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;

    public LFUEvictionPolicy(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void evict(Map<K, CacheEntry<K, V>> cacheMap) {

        if (cacheMap.size() >= capacity) {
            Iterator<Map.Entry<K, CacheEntry<K, V>>> iterator = cacheMap.entrySet().iterator();

            K leastFrequentKey = null;
            int minAccessCount = Integer.MAX_VALUE;
            long oldestAccessTime = Long.MAX_VALUE;

            while (iterator.hasNext()) {
                Map.Entry<K, CacheEntry<K, V>> entry = iterator.next();
                CacheEntry<K, V> cacheEntry = entry.getValue();
                int accessCount = cacheEntry.getAccessCount();
                long lastAccessTime = cacheEntry.getLastAccessedTime();

                if (accessCount < minAccessCount) {
                    minAccessCount = accessCount;
                    leastFrequentKey = entry.getKey();
                    oldestAccessTime = lastAccessTime;
                } else if (accessCount == minAccessCount) {
                    System.out.println("checking else if");
                    if (lastAccessTime < oldestAccessTime) {
                        leastFrequentKey = entry.getKey();
                        oldestAccessTime = lastAccessTime;
                    }
                }
            }

            if (leastFrequentKey != null) {
                cacheMap.remove(leastFrequentKey);
            }
        }

    }
}
