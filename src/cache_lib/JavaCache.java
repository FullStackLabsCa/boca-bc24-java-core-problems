package cache_lib;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, DataEntry<K,V>> dataStorage = new ConcurrentHashMap<>();
    @Getter
    private final String evictionPolicy;

    public JavaCache(String evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public void put(K key, V value){
        DataEntry entry = DataEntry.builder()
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();

        dataStorage.put(key, entry);
    }

    @Override
    public void put(K key, V value, long ttlDuration){
        DataEntry entry = DataEntry.builder()
                .ttlDuration(ttlDuration)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        dataStorage.put(key, entry);
    }

    @Override
    public V get(K key){
        DataEntry<K, V> dataEntry = dataStorage.get(key);
        V value = dataEntry.getValue();

        dataEntry.setLastAccessTime(LocalDateTime.now());
        dataStorage.put(key, dataEntry);

        return value;
    }

    @Override
    public boolean remove(K key){
        boolean successState = false;

        if(dataStorage.containsKey(key)){
            dataStorage.remove(key);
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
    }

    @Override
    public Set<K> keys(){
        return dataStorage.keySet();
    }
}