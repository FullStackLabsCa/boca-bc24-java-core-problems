package io.reactivestax.cachelibraryv2;

import java.util.Map;

public interface EvictionPolicy<K,V> {
    void evict(Map<K,CacheEntry<K,V>> cacheMap);
}
