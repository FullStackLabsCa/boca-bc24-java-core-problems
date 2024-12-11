package io.reactivestax.cachelibraryv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cache<K, V> implements CacheLibrary<K, V> {
    private final Map<K, CacheEntry<K, V>> cacheEntryMap;
    private EvictionPolicy<K, V> evictionPolicy;
    private long defaultTTLInSeconds = 60;
    private long customTtl;


    public Cache(EvictionPolicy<K,V> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
        this.cacheEntryMap = new HashMap<>();
    }

    @Override
    public boolean removeKey(K key) {
        return cacheEntryMap.remove(key) != null;
    }

    @Override
    public V getValue(K key) {
        CacheEntry<K, V> cacheEntry = cacheEntryMap.get(key);
        if (cacheEntry != null) {
            cacheEntry.updateAccessTime();
        //    cacheEntry.countFrequency();
            return cacheEntry.getValue();
        }
        return null;
    }

    @Override
    public int getSize() {
        return cacheEntryMap.size();
    }

    @Override
    public void clearCache() {
        cacheEntryMap.clear();
    }

    @Override
    public Set<K> retrieveKeys() {
        return cacheEntryMap.keySet();
    }

    @Override
    public void putKeyValueDefaultTtl(K key, V value) {
//        if (cacheEntryMap.size() >= capacity) {
//            evictionPolicy.evict(cacheEntryMap);
//        }
        CacheEntry<K, V> entry = new CacheEntry<>(key, value, defaultTTLInSeconds);
        applyEvictionPolicy();
        cacheEntryMap.put(key, entry);
    }

    @Override
    public void putKeyValueTTL(K key, V value, long ttl) {
//        if (cacheEntryMap.size() >= capacity) {
//            evictionPolicy.evict(cacheEntryMap);
//        }
        CacheEntry<K, V> entry = new CacheEntry<>(key, value, customTtl * 1000);
        applyEvictionPolicy();
        cacheEntryMap.put(key, entry);
    }

    @Override
    public void setEvictionPolicy(EvictionPolicy<K, V> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public void cleanExpiredValues() {
        //evictionPolicy.evict(cacheEntryMap);
    }

    @Override
    public void applyEvictionPolicy() {
            evictionPolicy.evict(cacheEntryMap);
    }
}
