package io.reactivestax.cachelibraryversion2;

public interface EvictionPolicy<K,V> {
    void evict(CacheLibrary<K,V> cache);
}
