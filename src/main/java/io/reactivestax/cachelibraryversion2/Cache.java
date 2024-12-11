package io.reactivestax.cachelibraryversion2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cache<K,V> implements CacheLibrary<K,V>{
    private final Map<K,V> cacheMap;
 //   private final EvictionPolicy<K, V> evictionPolicy;

    public Cache(){
       cacheMap = new HashMap<>();
    }

    @Override
    public void putKeyValue(K key, V value) {
        cacheMap.put(key,value);
    }


    @Override
    public V getValue(K key) {
        return cacheMap.get(key);
    }

    @Override
    public boolean removeKey(K key) {
        return cacheMap.remove(key) != null;
    }

    @Override
    public int getSize() {
        return cacheMap.size();
    }

    @Override
    public void clearCache() {

    }

    @Override
    public void setEvictionPolicy(EvictionPolicy<K, V> evictionPolicy) {

    }

    @Override
    public Set<K> retrieveKeys() {
        return Set.of();
    }

    @Override
    public void monitorEviction() {

    }

}
