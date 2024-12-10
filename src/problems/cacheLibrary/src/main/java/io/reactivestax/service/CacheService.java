package io.reactivestax.service;

import io.reactivestax.model.CacheEntryValue;

import java.util.Set;

public interface CacheService<K, V> {

    void put(K key, V value);

    void put(K key, V value, long ttl);

    CacheEntryValue<V> getById(K key);

    boolean removeById(K key);

    int getSizeofCache();

    void clearCache();

    Set<K> getAllKeys();
}
