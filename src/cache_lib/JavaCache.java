package cache_lib;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K extends Serializable, V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, DataEntry<K, V>> dataStorage = new ConcurrentHashMap<>();
    @Getter
    private final String evictionPolicy;
    private long defaultTtlDuration;
    @Getter
    private long defaultMaxSize;

    public JavaCache(String evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
        configureDefaultProperties(evictionPolicy);
        CacheManager.getInstance().initCacheManagement((JavaCache<Serializable, ?>) JavaCache.this);
    }

    private void configureDefaultProperties(String evictionPolicy) {
        if ("ttl".equals(evictionPolicy)) {
            defaultTtlDuration = 10;
            defaultMaxSize = -1;
        } else {
            defaultTtlDuration = -1;
            defaultMaxSize = 10;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key != null) {
            DataEntry<K, V> entry = DataEntry.<K, V>builder()
                    .key(key)
                    .value(value)
                    .ttlDuration(defaultTtlDuration)
                    .creationTime(LocalDateTime.now())
                    .lastAccessTime(LocalDateTime.now())
                    .maxSizePermitted(defaultMaxSize)
                    .build();

            dataStorage.put(key, entry);
        }
    }

    @Override
    public void put(K key, V value, long ttlDuration) {
        DataEntry<K, V> entry = DataEntry.<K, V>builder()
                .key(key)
                .value(value)
                .ttlDuration(ttlDuration)
                .creationTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .maxSizePermitted(defaultMaxSize)
                .build();
        dataStorage.put(key, entry);
    }

    @Override
    public V get(K key) {
        V value = null;
        if (key != null) {
            DataEntry<K, V> dataEntry = dataStorage.get(key);

            if (dataEntry != null) {
                value = dataEntry.getValue();
                long numAccess = dataEntry.getNumberOfTimesAccessed();
                dataEntry.setLastAccessTime(LocalDateTime.now());
                dataEntry.setNumberOfTimesAccessed(numAccess+1);
                dataStorage.put(key, dataEntry);
            }
        }
        return value;
    }

    public Collection<DataEntry<K, V>> getValues() {
        return dataStorage.values();
    }

    @Override
    public boolean remove(K key) {
        boolean successState = false;

        if (key != null && dataStorage.containsKey(key)) {
            dataStorage.remove(key);
            successState = true;
        }

        return successState;
    }

    @Override
    public int size() {
        return dataStorage.size();
    }

    @Override
    public void clear() {
        dataStorage.clear();
    }

    @Override
    public Set<K> keys() {
        return dataStorage.keySet();
    }
}