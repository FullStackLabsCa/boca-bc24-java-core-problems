package io.reactivestax.cachelibraryv1;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CacheManagement<K, V> implements Cache<K, V> {

    Map<K, CacheValue<V>> cacheValueMap;
    public long DEFAULT_TIME = 60;

    public CacheManagement(Map<K, CacheValue<V>> cacheValueMap, long DEFAULT_TIME) {
        this.cacheValueMap = cacheValueMap;
        this.DEFAULT_TIME = DEFAULT_TIME;

    }

    @Override
    public boolean removeKey(K key) {
        if (cacheValueMap.containsKey(key)) {
            cacheValueMap.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public V getValue(K key) {
        CacheValue<V> cacheValue = cacheValueMap.get(key);

        if (cacheValue == null || cacheValue.isExpired(System.currentTimeMillis())) {
            cacheValueMap.remove(key);
            return null;
        }
        cacheValue.lastTimeAccessed = System.currentTimeMillis();
        return cacheValue.value;
    }

    @Override
    public int getSize() {
        return cacheValueMap.size();
    }

    @Override
    public void doCleanup() {
        cacheValueMap.clear();
    }

    @Override
    public Set<K> retrieveKeys() {
        return cacheValueMap.keySet();
    }

    @Override
    public void putKeyValue(K key, V value) {
        CacheValue<V> cacheValue = new CacheValue<>(value, DEFAULT_TIME);
        cacheValueMap.put(key, cacheValue);
    }

    @Override
    public void putKeyValueTTL(K key, V value, long ttl) {
        CacheValue<V> cacheValue = new CacheValue<>(value, ttl);
        cacheValueMap.put(key, cacheValue);
    }

    public void cleanExpiredValues(){
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<K, CacheValue<V>>> cacheIterator = cacheValueMap.entrySet().iterator();
        while(cacheIterator.hasNext()){
            Map.Entry<K, CacheValue<V>> entry = cacheIterator.next();
           CacheValue<V> cacheValue = entry.getValue();
            if(cacheValue.isExpired(currentTime)){
                cacheIterator.remove();
            }
        }
    }

}

