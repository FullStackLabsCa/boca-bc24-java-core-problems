package cache_lib;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

public class DaemonFactory {

    private static DaemonFactory instance;

    public static synchronized DaemonFactory getInstance(){
        if(instance == null) instance = new DaemonFactory();
        return instance;
    }

    // every cache key is serialize
    // ? implements Serializable
    public void startTTLPolicyMonitoring(Collection<JavaCache<? super Serializable,?>> cacheCollection) {
        Thread ttlDaemonThread = new Thread(() -> {
            while (true) {
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

    public void startLRUPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {

    }

    public void startFIFOPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {

    }

    public void startLFUPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {

    }

    public void startRRPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {

    }

    public void startSizeBasedEvictionPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {

    }
}
