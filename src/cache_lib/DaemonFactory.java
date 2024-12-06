package cache_lib;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

public class DaemonFactory {

    private static DaemonFactory instance;

    public static synchronized DaemonFactory getInstance(){
        if(instance == null) instance = new DaemonFactory();
        return instance;
    }

    public void startTTLPolicyMonitoring(Collection<JavaCache<?,?>> cacheCollection) {
        Thread ttlDaemonThread = new Thread(() -> {
            while (true) {
                System.out.println("TTL Daemon thread running...");
                cacheCollection.iterator().forEachRemaining(
                        javaCache -> javaCache.getValues().iterator().forEachRemaining(
                                dataEntry -> {
                                    long ttl = dataEntry.getTtlDuration();
                                    Duration duration = Duration.between(dataEntry.getLastAccessTime(), LocalDateTime.now());
                                    if (duration.getSeconds() > ttl) {
                                        javaCache.remove(dataEntry.getKey());
                                    }
                                }
                        )
                );
            }
        });

        ttlDaemonThread.setDaemon(true);
        ttlDaemonThread.start();
    }

    public void startLRUPolicyMonitoring(Collection<JavaCache<?, ?>> cacheCollection) {

    }

    public void startFIFOPolicyMonitoring(Collection<JavaCache<?, ?>> cacheCollection) {

    }

    public void startLFUPolicyMonitoring(Collection<JavaCache<?, ?>> cacheCollection) {

    }

    public void startRRPolicyMonitoring(Collection<JavaCache<?, ?>> cacheCollection) {

    }

    public void startSizeBasedEvictionPolicyMonitoring(Collection<JavaCache<?, ?>> cacheCollection) {

    }
}
