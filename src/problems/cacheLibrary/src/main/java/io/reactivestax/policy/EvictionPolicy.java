package io.reactivestax.policy;

import io.reactivestax.service.Cache;

public interface EvictionPolicy<K, V> {
    void execute(Cache<K, V> cache);
}
