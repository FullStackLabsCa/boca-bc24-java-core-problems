package io.reactivestax.cachelibraryv2;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomEvictionPolicy<K,V> implements EvictionPolicy<K,V>{
    private final int capacity;
    private final Random random;

    public RandomEvictionPolicy(int capacity) {
        this.capacity = capacity;
        this.random = new Random();
    }

    @Override
    public void evict(Map<K, CacheEntry<K, V>> cacheMap) {
        if(cacheMap.size() >= capacity){
            Set<K> keys = cacheMap.keySet();
            int randomInt = random.nextInt(keys.size());
            K randomKey = (K) keys.toArray()[randomInt];
            cacheMap.remove(randomKey);
        }

    }
}
