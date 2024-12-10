package io.reactivestax.factory;

import io.reactivestax.policy.FIFO;
import io.reactivestax.policy.LFU;
import io.reactivestax.policy.LRU;
import io.reactivestax.policy.TTL;
import io.reactivestax.service.Cache;
import io.reactivestax.type.Eviction;


public class CacheFactory {
    public <K, V> Cache<K, V> createCache(String evictionPolicy) {
        Eviction eviction = Eviction.valueOf(evictionPolicy);
        return new Cache(new TTL<>());
    }

    public <K, V> Cache<K, V> createCache(String evictionPolicy, int capacity) {
        Eviction eviction = Eviction.valueOf(evictionPolicy);
        return switch (eviction) {
            case LRU -> new Cache<>(new LRU<>(capacity));
            case FIFO -> new Cache<>(new FIFO<>(capacity));
            case LFU -> new Cache<>(new LFU<>(capacity));
            default -> throw new RuntimeException("Incorrect eviction policy.");
        };
    }
}

