package io.reactivestax.cachelibraryv2;

import java.util.Iterator;
import java.util.Map;

public class TTLEvictionPolicy<K,V> implements EvictionPolicy<K,V>{
    private final Map<K, CacheEntry<K, V>> cacheEntryMap;

    public TTLEvictionPolicy(Map<K, CacheEntry<K, V>> cacheEntryMap) {
        this.cacheEntryMap = cacheEntryMap;
    }

    @Override
    public void evict(Map<K, CacheEntry<K, V>> cacheMap) {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<K, CacheEntry<K,V>>> cacheIterator = cacheEntryMap.entrySet().iterator();
        while(cacheIterator.hasNext()){
            Map.Entry<K, CacheEntry<K,V>> entry = cacheIterator.next();
            CacheEntry<K,V> cacheValue = entry.getValue();
            if(cacheValue.isExpired(currentTime)){
                cacheIterator.remove();
            }
        }
    }
}
