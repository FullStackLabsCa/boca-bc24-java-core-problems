package cache_lib;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, TTL> ttlManagement = new ConcurrentHashMap<>();

    public JavaCache() {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("My Daemon thread running...");
                ttlManagement.keySet().iterator().forEachRemaining(
                        key -> {
                            TTL ttl = ttlManagement.get(key);
                            Duration duration = Duration.between(ttl.getLastAccessTime(), LocalDateTime.now());
                            if(duration.getSeconds() > ttl.getTtlDuration()) {
                                cache.remove(key);
                                ttlManagement.remove(key);
                            }
                        }
                );
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class TTL{
    private long ttlDuration; //In Seconds
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
}