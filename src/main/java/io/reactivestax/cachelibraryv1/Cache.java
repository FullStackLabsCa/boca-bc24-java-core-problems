package io.reactivestax.cachelibraryv1;

import java.util.Set;

public interface Cache<K, V> {
    boolean removeKey(K key);

    V getValue(K key);

    int getSize();

    void doCleanup();

    Set<K> retrieveKeys();

    void putKeyValue(K key, V value);

    void putKeyValueTTL(K key, V value, long ttl);


}
