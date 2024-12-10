package cache_lib;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DaemonFactory {

    private static DaemonFactory instance;

    public static synchronized DaemonFactory getInstance() {
        if (instance == null) instance = new DaemonFactory();
        return instance;
    }

    // every cache key is serialize
    // ? implements Serializable
    public void startTTLPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {
        Thread ttlDaemonThread = new Thread(() -> {
            while (true) {
                cacheCollection.forEach(javaCache -> {
                    List<Serializable> keysToRemove = new ArrayList<>();
                    javaCache.getValues().forEach(dataEntry -> {
                        long ttl = dataEntry.getTtlDuration();
                        Duration duration = Duration.between(dataEntry.getLastAccessTime(), LocalDateTime.now());
                        if (duration.getSeconds() > ttl) {
                            keysToRemove.add(dataEntry.getKey());
                        }
                    });
                    keysToRemove.forEach(javaCache::remove);
                });
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
                cacheCollection.forEach(javaCache -> {
                    if (javaCache.size() > javaCache.getDefaultMaxSize()) {

                        Duration[] leastAccessTime = new Duration[1];
                        Serializable[] key = new Serializable[1];
                        LocalDateTime now = LocalDateTime.now();

                        // Iterate over data entries in a Cache
                        javaCache.getValues().iterator().forEachRemaining(
                                dataEntry -> {
                                    Duration duration = Duration.between(dataEntry.getLastAccessTime(), now);
                                    if (leastAccessTime[0] == null || duration.getNano() > leastAccessTime[0].getNano()) {
                                        leastAccessTime[0] = duration;
                                        key[0] = dataEntry.getKey();
                                    }
                                });
                        javaCache.remove(key[0]);
                    }}
                );
            }
        });

        lruDaemonThread.setDaemon(true);
        lruDaemonThread.start();
    }

    public void startFIFOPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {
        Thread fifoDaemonThread = new Thread(() -> {
            while (true) {
                cacheCollection.forEach(javaCache -> {
                    if (javaCache.size() > javaCache.getDefaultMaxSize()) {
                        Duration[] longestDuration = new Duration[1];
                        Serializable[] key = new Serializable[1];
                        LocalDateTime now = LocalDateTime.now();

                        // Iterate over data entries in a Cache
                        javaCache.getValues().iterator().forEachRemaining(
                                dataEntry -> {
                                    Duration duration = Duration.between(now, dataEntry.getCreationTime());
                                    if (longestDuration[0] == null || !durationGreaterThan(duration, longestDuration[0])) {
                                        longestDuration[0] = duration;
                                        key[0] = dataEntry.getKey();
                                    }
                                });
                        javaCache.remove(key[0]);
                    }}
                );
            }
        });

        fifoDaemonThread.setDaemon(true);
        fifoDaemonThread.start();
    }

    private boolean durationGreaterThan(Duration duration1, Duration duration2){
        if(duration1.getSeconds() > duration2.getSeconds()) return true;
        else if(duration1.getSeconds() == duration2.getSeconds()){
            return duration1.getNano() > duration2.getNano();
        }
        else return false;
    }

    public void startLFUPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {
        Thread lfuDaemonThread = new Thread(() -> {
            while (true) {
                cacheCollection.forEach(javaCache -> {
                    if (javaCache.size() > javaCache.getDefaultMaxSize()) {
                        AtomicLong minAccessTimes = new AtomicLong(Long.MAX_VALUE);
                        Serializable[] key = new Serializable[1];

                        // Iterate over data entries in a Cache
                        javaCache.getValues().iterator().forEachRemaining(
                                dataEntry -> {
                                    if (dataEntry.getNumberOfTimesAccessed() < minAccessTimes.get()) {
                                        minAccessTimes.set(dataEntry.getNumberOfTimesAccessed());
                                        key[0] = dataEntry.getKey();
                                    }
                                });
                        javaCache.remove(key[0]);
                    }}
                );
            }
        });

        lfuDaemonThread.setDaemon(true);
        lfuDaemonThread.start();
    }

    public void startRRPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {
        //Optional
    }

    public void startSizeBasedEvictionPolicyMonitoring(Collection<JavaCache<? super Serializable, ?>> cacheCollection) {
        //Optional
    }
}
