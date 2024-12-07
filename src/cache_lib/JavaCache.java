package cache_lib;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K extends Serializable,V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, DataEntry<K,V>> dataStorage = new ConcurrentHashMap<>();
    @Getter
    private final String evictionPolicy;
    private static final long DEFAULT_TTL_DURATION = 5;

    public JavaCache(String evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
        CacheManager.getInstance().initCacheManagement((JavaCache<Serializable, ?>) JavaCache.this);
    }

    @Override
    public void put(K key, V value){
        if(key != null) {
            DataEntry<K, V> entry = DataEntry.<K, V>builder()
                    .key(key)
                    .value(value)
                    .ttlDuration(DEFAULT_TTL_DURATION)
                    .creationTime(LocalDateTime.now())
                    .lastAccessTime(LocalDateTime.now())
                    .build();

            dataStorage.put(key, entry);
        }
    }

    @Override
    public void put(K key, V value, long ttlDuration){
        DataEntry<K,V> entry = DataEntry.<K,V>builder()
                .key(key)
                .value(value)
                .ttlDuration(ttlDuration)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        dataStorage.put(key, entry);
    }

    @Override
    public V get(K key){
        V value = null;
        if(key != null) {
            DataEntry<K, V> dataEntry = dataStorage.get(key);

            if (dataEntry != null) {
                value = dataEntry.getValue();
                dataEntry.setLastAccessTime(LocalDateTime.now());
                dataStorage.put(key, dataEntry);
            }
        }
        return value;
    }

    public Collection<DataEntry<K, V>> getValues(){
        return dataStorage.values();
    }

    @Override
    public boolean remove(K key){
        boolean successState = false;

        if(key!=null && dataStorage.containsKey(key)){
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