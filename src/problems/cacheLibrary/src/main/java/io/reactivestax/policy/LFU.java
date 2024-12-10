package io.reactivestax.policy;

import io.reactivestax.service.Cache;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public class LFU<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;

    public LFU(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void eviction(Cache<K, V> cache) {
        while (cache.getSizeofCache() > capacity) {
            Set<K> keys = cache.getAllKeys();
            Optional<K> lfuKey = keys.stream().peek(i -> System.out.println(cache.getEntry(i)))
                    .min(Comparator.comparing(key -> cache.getEntry(key).getCount()));

            lfuKey.ifPresent(cache::removeById);
        }
    }
}
