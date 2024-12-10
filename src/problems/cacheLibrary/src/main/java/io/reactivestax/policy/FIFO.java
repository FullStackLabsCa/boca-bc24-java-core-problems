package io.reactivestax.policy;

import io.reactivestax.service.Cache;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public class FIFO<K, V> implements EvictionPolicy<K, V> {
    private final int capacity;

    public FIFO(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void execute(Cache<K, V> cache) {
        while (cache.getSizeofCache() >= capacity) {
            Set<K> keys = cache.getAllKeys();
            Optional<K> oldestKey = keys.stream()
                    .min(Comparator.comparing(key -> cache.getEntry(key).getCreatedTime()));
            oldestKey.ifPresent(cache::removeById);
        }
    }
}
