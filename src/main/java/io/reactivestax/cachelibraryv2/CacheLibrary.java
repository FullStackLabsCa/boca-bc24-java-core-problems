package io.reactivestax.cachelibraryv2;


public interface CacheLibrary<K, V> {
    V getValue(K key);
    int getSize();
    void putKeyValueDefaultTtl(K key, V value);
    void putKeyValueTTL(K key, V value, long ttl);
    void applyEvictionPolicy();
}
