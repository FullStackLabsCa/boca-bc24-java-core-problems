package io.reactivestax.policy;

import io.reactivestax.service.Cache;

public class LFU<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;

    public LFU(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void execute(Cache<K, V> cache) {

    }
}
