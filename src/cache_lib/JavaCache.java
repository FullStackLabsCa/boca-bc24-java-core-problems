package cache_lib;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, TTL> ttlManagement = new ConcurrentHashMap<>();

    public JavaCache() {
        DaemonFactory<K, V> daemonFactory = new DaemonFactory<>();
        Thread ttlDaemonThread = daemonFactory.ttlDaemonPolicy(ttlManagement, cache);

        ttlDaemonThread.start();
    }

    @Override
    public void put(K key, V value){
        cache.put(key, value);
        TTL ttl = TTL.builder()
                .ttlDuration(60)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public void put(K key, V value, long ttlDuration){
        cache.put(key, value);
        TTL ttl = TTL.builder()
                .ttlDuration(ttlDuration)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public V get(K key){
        V value = cache.get(key);

        TTL ttl = ttlManagement.get(key);
        ttl.setLastAccessTime(LocalDateTime.now());
        ttlManagement.put(key, ttl);

        return value;
    }

    @Override
    public boolean remove(K key){
        boolean successState = false;

        if(cache.containsKey(key)){
            cache.remove(key);
            ttlManagement.remove(key);
            successState = true;
        }

        return successState;
    }

    @Override
    public int size(){
        return cache.size();
    }

    @Override
    public void clear(){
        cache.clear();
        ttlManagement.clear();
    }

    @Override
    public Set<K> keys(){
        return cache.keySet();
    }
}