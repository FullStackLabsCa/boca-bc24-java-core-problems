package io.reactivestax.cachelibraryversion2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TTLEvictionPolicy<K,V> implements EvictionPolicy<K,V> {
    private int ttl;
    private final Map<K,CacheEntry<V>> cacheMap;


    public TTLEvictionPolicy(int ttl) {
        this.ttl = ttl;
        this.cacheMap = new HashMap<>();
    }

    @Override
    public void evict(CacheLibrary<K, V> cache) {
    long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<K, CacheEntry<V>>> cacheIterator = cacheMap.entrySet().iterator();
        while(cacheIterator.hasNext()){
            Map.Entry<K, CacheEntry<V>> entry = cacheIterator.next();
            CacheEntry<V> cacheValue = entry.getValue();
            if(cacheValue.isExpired(currentTime)){
                cacheIterator.remove();
            }
        }
    }
}
