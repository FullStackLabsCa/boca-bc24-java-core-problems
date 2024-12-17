package io.reactivestax.cachelibraryv2;

import java.util.HashMap;
import java.util.Map;


public class Cache<K, V> implements CacheLibrary<K, V> {
    private final Map<K, CacheEntry<K, V>> cacheEntryMap;
    private EvictionPolicy<K, V> evictionPolicy;
    private long defaultTTLInSeconds = 60;


    public Cache(EvictionPolicy<K,V> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
        this.cacheEntryMap = new HashMap<>();

    }

    @Override
    public V getValue(K key) {
        CacheEntry<K, V> cacheEntry = cacheEntryMap.get(key);
        if (cacheEntry != null) {
            cacheEntry.updateAccessTime();
            return cacheEntry.getValue();
        }
        return null;
    }


    @Override
    public int getSize() {
        return cacheEntryMap.size();
    }

    @Override
    public void putKeyValueDefaultTtl(K key, V value) {
        CacheEntry<K, V> entry = new CacheEntry<>(key, value, defaultTTLInSeconds);
            applyEvictionPolicy();
        cacheEntryMap.put(key, entry);
    }

    @Override
    public void putKeyValueTTL(K key, V value, long ttl ) {
       CacheEntry<K, V> entry = new CacheEntry<>(key, value, ttl);
       applyEvictionPolicy();
       cacheEntryMap.put(key, entry);
    }

    @Override
    public void applyEvictionPolicy() {
            evictionPolicy.evict(cacheEntryMap);
    }
}
