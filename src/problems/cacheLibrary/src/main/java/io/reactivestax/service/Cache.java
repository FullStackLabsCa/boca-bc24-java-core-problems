package io.reactivestax.service;

import io.reactivestax.model.CacheEntryValue;
import io.reactivestax.policy.EvictionPolicy;
import io.reactivestax.policy.LFU;
import io.reactivestax.policy.TTL;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> implements CacheService<K, V> {
    private static final int DEFAULT_TTL = 60;
    private final Map<K, CacheEntryValue<V>> cache = new ConcurrentHashMap<>(16, 0.75f);

    EvictionPolicy<K, V> evictionPolicy;

    public Cache(EvictionPolicy<K, V> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;

        if (evictionPolicy instanceof TTL<K, V>) {
            Thread ttlDaemonThread = new Thread(this::cleanupExpiredEntries);
            ttlDaemonThread.setDaemon(true);
            ttlDaemonThread.start();
        }
    }

    @Override
    public void put(K key, V value) {
        if (evictionPolicy instanceof TTL<K, V>) {
            put(key, value, DEFAULT_TTL);
        } else {
            CacheEntryValue<V> entryValue = new CacheEntryValue<>(value);
            evictionPolicy.execute(this);
            cache.put(key, entryValue);
        }
    }

    @Override
    public void put(K key, V value, long ttl) {
        CacheEntryValue<V> entryValue = new CacheEntryValue<>(value, ttl);
        evictionPolicy.execute(this);
        cache.put(key, entryValue);

    }

    @Override
    public CacheEntryValue<V> getById(K key) {
        CacheEntryValue<V> cacheEntryValue = cache.get(key);

        if (cacheEntryValue != null) {
            if (evictionPolicy instanceof TTL<K, V>) {
                if (cacheEntryValue.isExpired()) {
                    cache.remove(key);
                    return null;
                }
            }
            if (evictionPolicy instanceof LFU<K,V>) {
                cacheEntryValue.increaseCount();
            }
            cacheEntryValue.updateLastAccessTime();
            return cacheEntryValue;
        }
        return null;
    }

    public CacheEntryValue<V> getEntry(K key) {
        return cache.get(key);
    }

    @Override
    public boolean removeById(K key) {
        return cache.remove(key) != null;
    }

    @Override
    public int getSizeofCache() {
        return cache.size();
    }

    @Override
    public void clearCache() {
        cache.clear();
    }

    @Override
    public Set<K> getAllKeys() {
        return cache.keySet();
    }

    private void cleanupExpiredEntries() {
        while (true) {
            try {
                for (K key : cache.keySet()) {
                    CacheEntryValue<V> entry = cache.get(key);
                    if (entry != null && entry.isExpired()) {
                        cache.remove(key);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}