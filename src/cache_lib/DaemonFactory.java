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
        /**
         * Least Recently Used:
         * Monitor the last access time
         * Maintain a variable for the thread that keeps a track of Min(Current Time - Last Access Time), and KEY
         * Once the iteration through all the objects is cleared off, the Key saved is removed!
         * The next iteration starts
         */
        Thread lruDaemonThread = new Thread(() -> {
            while (true) {
                cacheCollection.iterator().forEachRemaining(
                        javaCache -> {
                            Duration[] leastAccessTime = new Duration[1];
                            Serializable[] key = new Serializable[1];

                            // Iterate over data entries in a Cache
                            javaCache.getValues().iterator().forEachRemaining(
                                    dataEntry -> {
                                        if (javaCache.size() > dataEntry.getMaxSizePermitted()) {
                                            Duration duration = Duration.between(dataEntry.getLastAccessTime(), LocalDateTime.now());
                                            if (leastAccessTime[0] == null || duration.getSeconds() < leastAccessTime[0].getSeconds()) {
                                                leastAccessTime[0] = duration;
                                                key[0] = dataEntry.getKey();
                                            }
                                        }
                                    });
                            javaCache.remove(key);
                        }
                );
            }
        });

        lruDaemonThread.setDaemon(true);
        lruDaemonThread.start();
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
