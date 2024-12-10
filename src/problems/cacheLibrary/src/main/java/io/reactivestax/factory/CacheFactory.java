package io.reactivestax.factory;

import io.reactivestax.policy.*;
import io.reactivestax.service.Cache;
import io.reactivestax.type.Eviction;


public class CacheFactory {

    public static <K, V> Cache<K, V> createCache(String evictionPolicy, int... capacity) {
        Eviction eviction = Eviction.valueOf(evictionPolicy);
        return switch (eviction) {
            case TTL -> new Cache<>(new TTL<>());
            case LRU -> new Cache<>(new LRU<>(capacity[0]));
            case FIFO -> new Cache<>(new FIFO<>(capacity[0]));
            case LFU -> new Cache<>(new LFU<>(capacity[0]));
            case RR -> new Cache<>(new RR<>(capacity[0]));
            case SB -> new Cache<>(new SB<>(capacity[0]));
        };
    }
}

