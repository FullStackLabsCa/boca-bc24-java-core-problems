package cache_lib;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final HashMap<K, V> dataStorage = new HashMap<>();
    private final HashMap<K, TTL> ttlManagement = new HashMap<>();

    private JavaCache() {
    }

    @Override
    public void put(K key, V value){
        dataStorage.put(key, value);
        TTL ttl = TTL.builder()
                .ttlDuration(60)
                .createdTime(LocalDateTime.now())
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public void put(K key, V value, int ttlDuration){
        dataStorage.put(key, value);
        TTL ttl = TTL.builder()
                .ttlDuration(ttlDuration)
                .createdTime(LocalDateTime.now())
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class TTL{
    private int ttlDuration; //In Seconds
    private LocalDateTime createdTime;
    private LocalDateTime lastAccessTime;
}
