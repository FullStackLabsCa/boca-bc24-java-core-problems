package io.reactivestax.cachelibraryv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cache<K, V> implements CacheLibrary<K, V> {
    private final Map<K, CacheEntry<K, V>> cacheEntryMap;
    private EvictionPolicy<K, V> evictionPolicy;
    private long defaultTTLInSeconds = 60;
 //   private long customTtl;

    public Cache(EvictionPolicy<K,V> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
        this.cacheEntryMap = new HashMap<>();
    //    this.customTtl = 1;

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
    public Set<K> retrieveKeys() {
        return cacheEntryMap.keySet();
    }

    @Override
    public boolean removeKey(K key) {
        if (cacheEntryMap.containsKey(key)) {
            cacheEntryMap.remove(key);
            return true;
        }
        return false;
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
//    public void cleanExpiredValues() {
//        long currentTime = System.currentTimeMillis();
//        Iterator<Map.Entry<K, CacheEntry<K,V>>> cacheIterator = cacheEntryMap.entrySet().iterator();
//        while (cacheIterator.hasNext()) {
//            Map.Entry<K, CacheEntry<K,V>> entry = cacheIterator.next();
//            CacheEntry<K,V> cacheValue = entry.getValue();
//            if (cacheValue.isExpired(currentTime)) {
//                cacheIterator.remove();
//                cacheEntryMap.clear();
//            }
//        }
//    }