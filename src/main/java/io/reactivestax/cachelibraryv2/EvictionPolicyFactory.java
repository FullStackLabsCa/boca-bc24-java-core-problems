package io.reactivestax.cachelibraryv2;

import java.util.concurrent.ConcurrentHashMap;

public class EvictionPolicyFactory {

    public static <K, V> CacheLibrary<K, V> createCache(String evictionPolicy, int capacity) {
        EvictionPolicy<K, V> evictionPolicyType;
        switch (evictionPolicy) {
            case "LRU":
                evictionPolicyType = new LRUEvictionPolicy<>(capacity);
                break;
            case "LFU":
                evictionPolicyType = new LFUEvictionPolicy(capacity);
                break;
            case "TTL":
                evictionPolicyType = new TTLEvictionPolicy<>(new ConcurrentHashMap<>());
                break;
            case "FIFO":
                evictionPolicyType = new FIFOEvictionPolicy<>(capacity);
                break;
            case "RANDOM":
                evictionPolicyType = new RandomEvictionPolicy<>(capacity);
                break;
            default:
                throw new IllegalArgumentException("Eviction policy not found");
        }
        return new Cache<>(evictionPolicyType);
    }
}
