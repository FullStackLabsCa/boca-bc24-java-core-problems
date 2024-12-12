package io.reactivestax.cachelibraryv2;

import java.util.Set;

public interface CacheLibrary<K, V> {
    V getValue(K key);
    Set<K> retrieveKeys();
    boolean removeKey(K key);
    int getSize();
    void putKeyValueDefaultTtl(K key, V value);
    void putKeyValueTTL(K key, V value, long ttl);
    void applyEvictionPolicy();
}
