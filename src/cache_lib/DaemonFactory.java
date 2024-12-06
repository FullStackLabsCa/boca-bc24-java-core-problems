package cache_lib;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

public class DaemonFactory {

    public Thread ttlPolicyMonitorProvider(Collection<HashMap<?, DataEntry<?, ?>>> cacheCollection) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("TTL Daemon thread running...");
                cacheCollection.iterator().forEachRemaining(
                        cache -> cache.values().iterator().forEachRemaining(
                                dataEntry -> {
                                    long ttl = dataEntry.getTtlDuration();
                                    Duration duration = Duration.between(dataEntry.getLastAccessTime(), LocalDateTime.now());
                                    if (duration.getSeconds() > ttl) {
                                        cache.remove(dataEntry.getKey());
                                    }
                                }
                        )
                );
            }
        });

        daemonThread.setDaemon(true);
        return daemonThread;
    }
}
