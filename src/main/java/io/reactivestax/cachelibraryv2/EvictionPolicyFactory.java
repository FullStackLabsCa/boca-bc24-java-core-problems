package io.reactivestax.cachelibraryv2;

import java.util.concurrent.ConcurrentHashMap;

public class EvictionPolicyFactory {

    public static <K, V> CacheLibrary<K, V> createCache(String evictionPolicy, int capacityOrTTL) {
        EvictionPolicy<K, V> evictionPolicyType;
        switch (evictionPolicy) {
            case "LRU":
                evictionPolicyType = new LRUEvictionPolicy<>(capacityOrTTL);
                break;
            case "LFU":
                evictionPolicyType = new LFUEvictionPolicy(capacityOrTTL);
                break;
            case "TTL":
                evictionPolicyType = new TTLEvictionPolicy<>();
                break;
            case "FIFO":
                evictionPolicyType = new FIFOEvictionPolicy<>(capacityOrTTL);
                break;
            case "RANDOM":
                evictionPolicyType = new RandomEvictionPolicy<>(capacityOrTTL);
                break;
            default:
                throw new IllegalArgumentException("Eviction policy not found");
        }
        return new Cache<>(evictionPolicyType);
    }
}
