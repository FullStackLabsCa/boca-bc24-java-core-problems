package io.reactivestax.cachelibraryv2;

import java.util.Set;

public interface CacheLibrary<K, V> {
    boolean removeKey(K key);
    V getValue(K key);
    int getSize();
    void clearCache();
    Set<K> retrieveKeys();
    void putKeyValueDefaultTtl(K key, V value);
    void putKeyValueTTL(K key, V value, long ttl);
    void setEvictionPolicy(EvictionPolicy<K,V> evictionPolicy);
    void cleanExpiredValues();
    void applyEvictionPolicy();
}
