package io.reactivestax.cachelibraryversion2;

import java.util.Set;

public interface CacheLibrary<K, V> {
    void putKeyValue(K key, V value);

    V getValue(K key);

    boolean removeKey(K key);

    int getSize();

    void clearCache();

    void setEvictionPolicy(EvictionPolicy<K, V> evictionPolicy);

    Set<K> retrieveKeys();

    void monitorEviction();
}
