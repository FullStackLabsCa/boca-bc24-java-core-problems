package cache_lib;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, V> dataStorage = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, TTL> ttlManagement = new ConcurrentHashMap<>();
    private static final long DEFAULT_TTL_DURATION = 60;

    public JavaCache() {
        DaemonFactory<K, V> daemonFactory = new DaemonFactory<>();
        Thread ttlDaemonThread = daemonFactory.ttlDaemonPolicy(ttlManagement, dataStorage);

        ttlDaemonThread.start();
    }

    @Override
    public void put(K key, V value){
        dataStorage.put(key, value);
        TTL ttl = TTL.builder()
                .ttlDuration(DEFAULT_TTL_DURATION)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public void put(K key, V value, long ttlDuration){
        dataStorage.put(key, value);
        TTL ttl = TTL.builder()
                .ttlDuration(ttlDuration)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public V get(K key){
        V value = dataStorage.get(key);

        TTL ttl = ttlManagement.get(key);
        ttl.setLastAccessTime(LocalDateTime.now());
        ttlManagement.put(key, ttl);

        return value;
    }

    @Override
    public boolean remove(K key){
        boolean successState = false;

        if(dataStorage.containsKey(key)){
            dataStorage.remove(key);
            ttlManagement.remove(key);
            successState = true;
        }

        return successState;
    }

    @Override
    public int size(){
        return dataStorage.size();
    }

    @Override
    public void clear(){
        dataStorage.clear();
        ttlManagement.clear();
    }

    @Override
    public Set<K> keys(){
        return dataStorage.keySet();
    }
}