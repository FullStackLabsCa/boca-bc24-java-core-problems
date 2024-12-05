package cache_lib;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

public class JavaCache<K,V> implements CacheLibrary<K, V> {
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, TTL<K>> ttlManagement = new ConcurrentHashMap<>();

    public JavaCache() {
        KeyRemovalManager<Object> keyRemovalManager = KeyRemovalManager.getInstance();
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("My Daemon thread running...");
                K keyToRemove = (K) keyRemovalManager.getKeyToRemove();
                cache.remove(keyToRemove);
                ttlManagement.remove(keyToRemove);
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void put(K key, V value){
        cache.put(key, value);
        TTL ttl = TTL.builder()
                .key(key)
                .ttlDuration(60)
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public void put(K key, V value, long ttlDuration){
        cache.put(key, value);
        TTL ttl = TTL.builder()
                .key(key)
                .ttlDuration(ttlDuration)
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, ttl);
    }

    @Override
    public V get(K key){
        V value = cache.get(key);

        TTL<K> ttl = ttlManagement.get(key);
        TTL newTTL = TTL.builder()
                .key(key)
                .ttlDuration(ttl.getTtlDuration())
                .lastAccessTime(LocalDateTime.now())
                .build();
        ttlManagement.put(key, newTTL);

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
@NoArgsConstructor
@Getter
@Setter
class TTL<K> {
    private K key;
    private long ttlDuration; //In Seconds
    private LocalDateTime lastAccessTime;

    public TTL(K key, long ttlDuration, LocalDateTime lastAccessTime) {
        this.key = key;
        this.ttlDuration = ttlDuration;
        this.lastAccessTime = lastAccessTime;

        if(ttlDuration != -1) {
            Executors.newSingleThreadScheduledExecutor().schedule(() -> (KeyRemovalManager.getInstance()).submitKeyForRemoval(key), ttlDuration, TimeUnit.SECONDS);
        }
    }
}

class KeyRemovalManager<K> {
    private final LinkedBlockingDeque<K> keysToRemove = new LinkedBlockingDeque<>();

    private static KeyRemovalManager<Object> instance;

    private KeyRemovalManager() {
    }

    public static synchronized KeyRemovalManager<Object> getInstance(){
        if(instance==null) instance = new KeyRemovalManager<>();
        return instance;
    }

    public K getKeyToRemove() {
        try {
            return keysToRemove.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void submitKeyForRemoval(K key) {
        try {
            keysToRemove.put(key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}