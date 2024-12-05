package cache_lib;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentMap;

public class DaemonFactory<K,V> {

    public Thread ttlDaemonPolicy(ConcurrentMap<K,TTL> ttlManagement, ConcurrentMap<K,V> cache){
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("My Daemon thread running...");
                ttlManagement.keySet().iterator().forEachRemaining(
                        key -> {
                            TTL ttl = ttlManagement.get(key);
                            Duration duration = Duration.between(ttl.getLastAccessTime(), LocalDateTime.now());
                            if (duration.getSeconds() > ttl.getTtlDuration()) {
                                cache.remove(key);
                                ttlManagement.remove(key);
                            }
                        }
                );
            }
        });

        daemonThread.setDaemon(true);
        return daemonThread;
    }
}
