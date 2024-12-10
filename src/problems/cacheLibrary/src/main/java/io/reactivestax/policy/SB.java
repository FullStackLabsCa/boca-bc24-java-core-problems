package io.reactivestax.policy;

import io.reactivestax.service.Cache;

public class SB<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;

    public SB(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void eviction(Cache<K, V> cache) {
        while (cache.getSizeofCache() >= capacity) {

        }
    }
}
