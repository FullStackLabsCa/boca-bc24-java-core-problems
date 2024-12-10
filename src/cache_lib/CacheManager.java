package cache_lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CacheManager {

    private static CacheManager instance;
    private volatile Collection<JavaCache<? super Serializable, ?>> ttlCaches;
    private volatile Collection<JavaCache<? super Serializable, ?>> lruCaches;
    private volatile Collection<JavaCache<? super Serializable, ?>> fifoCaches;
    private volatile Collection<JavaCache<? super Serializable, ?>> lfuCaches;
    private volatile Collection<JavaCache<? super Serializable, ?>> rrCaches;

    public static synchronized CacheManager getInstance() {
        if (instance == null) instance = new CacheManager();
        return instance;
    }

    public void initCacheManagement(JavaCache<Serializable, ?> javaCache) {
        String policy = javaCache.getEvictionPolicy();
        Collection<JavaCache<? super Serializable, ?>> cacheCollection = initializeCache(policy);
        cacheCollection.add(javaCache);
    }

    private Collection<JavaCache<? super Serializable, ?>> initializeCache(String policy) {
        return switch (policy) {
            case "ttl" -> initTTLMonitoringForCaches();
            case "lru" -> initLRUMonitoringForCaches();
            case "lfu" -> initLFUMonitoringForCaches();
            case "fifo" -> initFIFOMonitoringForCaches();
            case "rr" -> initRRMonitoringForCaches();
            default -> throw new RuntimeException("Invalid Eviction Policy");
        };
    }

    private Collection<JavaCache<? super Serializable, ?>> initTTLMonitoringForCaches() {
        if (ttlCaches == null) {
            ttlCaches = new ArrayList<>();
            DaemonFactory.getInstance().startTTLPolicyMonitoring(ttlCaches);
        }
        return ttlCaches;
    }

    private Collection<JavaCache<? super Serializable, ?>> initLRUMonitoringForCaches() {
        if (lruCaches == null) {
            lruCaches = new ArrayList<>();
            DaemonFactory.getInstance().startLRUPolicyMonitoring(lruCaches);
        }
        return lruCaches;
    }

    private Collection<JavaCache<? super Serializable, ?>> initFIFOMonitoringForCaches() {
        if (fifoCaches == null) {
            fifoCaches = new ArrayList<>();
            DaemonFactory.getInstance().startFIFOPolicyMonitoring(fifoCaches);
        }
        return fifoCaches;
    }

    private Collection<JavaCache<? super Serializable, ?>> initLFUMonitoringForCaches() {
        if (lfuCaches == null) {
            lfuCaches = new ArrayList<>();
            DaemonFactory.getInstance().startLFUPolicyMonitoring(lfuCaches);
        }
        return lfuCaches;
    }

    private Collection<JavaCache<? super Serializable, ?>> initRRMonitoringForCaches() {
        if (rrCaches == null) {
            rrCaches = new ArrayList<>();
            DaemonFactory.getInstance().startRRPolicyMonitoring(rrCaches);
        }
        return rrCaches;
    }

    public void resetCacheManager() {
        ttlCaches = null;
        lruCaches = null;
        lfuCaches = null;
        fifoCaches = null;
        rrCaches = null;

    }

}
