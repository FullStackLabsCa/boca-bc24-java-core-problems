package io.reactivestax.factory;

import io.reactivestax.policy.*;
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
            case RR -> new Cache<>(new RR<>(capacity));
            case SB -> new Cache<>(new SB<>(capacity));
            default -> throw new RuntimeException("Incorrect eviction policy.");
        };
    }
}

