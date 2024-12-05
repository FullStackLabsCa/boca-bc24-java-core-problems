package cache_lib;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final HashMap<K, V> dataStorage = new HashMap<>();
    private final HashMap<K, TTL> ttlManagement = new HashMap<>();

    private final LinkedBlockingDeque<K> keysToRemove = new LinkedBlockingDeque<>();

    private JavaCache() {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("My Daemon thread running...");
                try {
                    K keyToRemove = keysToRemove.take();
                    dataStorage.remove(keyToRemove);
                    ttlManagement.remove(keyToRemove);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
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
