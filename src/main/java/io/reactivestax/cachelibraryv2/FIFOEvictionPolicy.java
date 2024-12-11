package io.reactivestax.cachelibraryv2;

import java.util.Iterator;
import java.util.Map;

public class FIFOEvictionPolicy<K,V> implements EvictionPolicy<K,V> {
    private final int capacity;

    public FIFOEvictionPolicy(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void evict(Map<K, CacheEntry<K, V>> cacheMap) {
    if(cacheMap.size() >= capacity){
        Iterator<Map.Entry<K, CacheEntry<K,V>>> iterator = cacheMap.entrySet().iterator();
        if(iterator.hasNext()){
            Map.Entry<K, CacheEntry<K, V>> entry = iterator.next();
            iterator.remove();
        }
    }
    }
}
