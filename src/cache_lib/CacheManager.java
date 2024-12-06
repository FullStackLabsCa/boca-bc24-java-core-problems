package cache_lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CacheManager {

    private static CacheManager instance;

    public static synchronized CacheManager getInstance() {
        if (instance == null) instance = new CacheManager();
        return instance;
    }

    /**
     * it create a given cache object
     * also maintain the cache object references
     *
     * Dameon can get list of all caches managed/maintain and perform the house cleaning
     *
     */

    private static Collection<JavaCache<? super Serializable,?>> ttlCaches;
    private static Collection<JavaCache<? super Serializable,?>> lruCaches;
    private static Collection<JavaCache<? super Serializable,?>> fifoCaches;
    private static Collection<JavaCache<? super Serializable,?>> lfuCaches;
    private static Collection<JavaCache<? super Serializable,?>> rrCaches;
    private static Collection<JavaCache<? super Serializable,?>> sizeBasedEvictionCaches;


    /**
     *  factory of creating different tyoe of cache
     *  - TTL
     *  - LRU
     *  - LFU
     *
     */
    public void initCacheManagement(JavaCache<?, ?> javaCache){
        /**
         * Check the Policy of the Cache
         * Based on the Policy: check if the policy is initiated
         * If initiated: add the cache object to the policy collection
         * If not: initiate the policy's collection and add the cache object to it
         */
        String policy = javaCache.getEvictionPolicy();
        Collection<JavaCache<? super Serializable,?>> cacheCollection = checkIfInitialized(policy);
        if(cacheCollection != null) cacheCollection.add(javaCache);
        else {
            Collection<JavaCache<? super Serializable, ?>> javaCaches = initPolicy(policy);
            javaCaches.add(javaCache);
        }
    }

    private Collection<JavaCache<? super Serializable,?>> initPolicy(String policy) {
        switch (policy){
            case "ttl":
                return initTTLMonitoringForCaches();
                break;
            case "lru":
                initLRUMonitoringForCaches();
                break;
            case "lfu":
                initLFUMonitoringForCaches();
                break;
            case "fifo":
                initFIFOMonitoringForCaches();
                break;
            case "rr":
                initRRMonitoringForCaches();
                break;
            case "sizeBased":
                initSizeBasedEvictionMonitoringForCaches();
                break;
            default:
                throw new RuntimeException("Invalid Eviction Policy Mentioned!!!");
        }
    }

    private Collection<JavaCache<? super Serializable, ?>> checkIfInitialized(String policy) {
        return null;
    }


    private static Collection<JavaCache<? super Serializable,?>> initTTLMonitoringForCaches(){
        ttlCaches = new ArrayList<>();
        DaemonFactory.getInstance().startTTLPolicyMonitoring(ttlCaches);
        return ttlCaches;
    }

    private static void initLRUMonitoringForCaches(){
        lruCaches = new ArrayList<>();
        DaemonFactory.getInstance().startLRUPolicyMonitoring(lruCaches);
    }

    private static void initFIFOMonitoringForCaches(){
        fifoCaches = new ArrayList<>();
        DaemonFactory.getInstance().startFIFOPolicyMonitoring(fifoCaches);
    }

    private static void initLFUMonitoringForCaches(){
        lfuCaches = new ArrayList<>();
        DaemonFactory.getInstance().startLFUPolicyMonitoring(lfuCaches);
    }

    private static void initRRMonitoringForCaches(){
        rrCaches = new ArrayList<>();
        DaemonFactory.getInstance().startRRPolicyMonitoring(rrCaches);
    }

    private static void initSizeBasedEvictionMonitoringForCaches(){
        sizeBasedEvictionCaches = new ArrayList<>();
        DaemonFactory.getInstance().startSizeBasedEvictionPolicyMonitoring(sizeBasedEvictionCaches);
    }

}
