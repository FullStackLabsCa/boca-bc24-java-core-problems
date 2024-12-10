package io.reactivestax.policy;

import io.reactivestax.service.Cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RR<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;

    public RR(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void execute(Cache<K, V> cache) {
        while (cache.getSizeofCache() >= capacity) {
            List<K> keys = new ArrayList<>(cache.getAllKeys().stream().toList());

            Collections.shuffle(keys);

            K key = keys.get(0);
            cache.removeById(key);
        }
    }
}
