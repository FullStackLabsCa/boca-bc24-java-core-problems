package cache_lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CacheManager {

    private static CacheManager instance;
    private static Collection<JavaCache<? super Serializable, ?>> ttlCaches;
    private static Collection<JavaCache<? super Serializable, ?>> lruCaches;
    private static Collection<JavaCache<? super Serializable, ?>> fifoCaches;
    private static Collection<JavaCache<? super Serializable, ?>> lfuCaches;
    private static Collection<JavaCache<? super Serializable, ?>> rrCaches;
    private static Collection<JavaCache<? super Serializable, ?>> sizeBasedEvictionCaches;

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
        switch (policy) {
            case "ttl":
                if (ttlCaches == null) return initTTLMonitoringForCaches();
                else return ttlCaches;
            case "lru":
                if (lruCaches == null) return initLRUMonitoringForCaches();
                else return lruCaches;
            case "lfu":
                if (lfuCaches == null) return initLFUMonitoringForCaches();
                else return lfuCaches;
            case "fifo":
                if (fifoCaches == null) return initFIFOMonitoringForCaches();
                else return fifoCaches;
            case "rr":
                if (rrCaches == null) return initRRMonitoringForCaches();
                else return rrCaches;
            case "sizeBased":
                if (sizeBasedEvictionCaches == null) return initSizeBasedEvictionMonitoringForCaches();
                else return sizeBasedEvictionCaches;
            default:
                throw new RuntimeException("Invalid Eviction Policy");
        }
    }


    private static Collection<JavaCache<? super Serializable, ?>> initTTLMonitoringForCaches() {
        ttlCaches = new ArrayList<>();
        DaemonFactory.getInstance().startTTLPolicyMonitoring(ttlCaches);
        return ttlCaches;
    }

    private static Collection<JavaCache<? super Serializable, ?>> initLRUMonitoringForCaches() {
        lruCaches = new ArrayList<>();
        DaemonFactory.getInstance().startLRUPolicyMonitoring(lruCaches);
        return lruCaches;
    }

    private static Collection<JavaCache<? super Serializable, ?>> initFIFOMonitoringForCaches() {
        fifoCaches = new ArrayList<>();
        DaemonFactory.getInstance().startFIFOPolicyMonitoring(fifoCaches);
        return fifoCaches;
    }

    private static Collection<JavaCache<? super Serializable, ?>> initLFUMonitoringForCaches() {
        lfuCaches = new ArrayList<>();
        DaemonFactory.getInstance().startLFUPolicyMonitoring(lfuCaches);
        return lfuCaches;
    }

    private static Collection<JavaCache<? super Serializable, ?>> initRRMonitoringForCaches() {
        rrCaches = new ArrayList<>();
        DaemonFactory.getInstance().startRRPolicyMonitoring(rrCaches);
        return rrCaches;
    }

    private static Collection<JavaCache<? super Serializable, ?>> initSizeBasedEvictionMonitoringForCaches() {
        sizeBasedEvictionCaches = new ArrayList<>();
        DaemonFactory.getInstance().startSizeBasedEvictionPolicyMonitoring(sizeBasedEvictionCaches);
        return sizeBasedEvictionCaches;
    }

    public void resetCacheManager() {
        ttlCaches = null;
        lruCaches = null;
        lfuCaches = null;
        sizeBasedEvictionCaches = null;
        fifoCaches = null;
        rrCaches = null;

    }

}
