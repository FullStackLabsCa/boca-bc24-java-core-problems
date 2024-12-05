package cache_lib;

import java.util.Set;

public interface CacheLibrary<K, V> {
    void put(K key, V value);
    void put(K key, V value, int ttlDuration);
    V get(K key);
    boolean remove(K key);
    int size();
    void clear();
    Set<K> keys();
}
