package cache_lib;

import java.io.Serializable;
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
    public void initCacheManagement(JavaCache<? super Serializable, ?> javaCache){
        //TODO
    }


    private void initTTLMonitoringForCaches(Collection<JavaCache<? super Serializable,?>> cacheCollection){
        DaemonFactory.getInstance().startTTLPolicyMonitoring(cacheCollection);
    }

    private void initLRUMonitoringForCaches(Collection<JavaCache<? super Serializable,?>> cacheCollection){
        DaemonFactory.getInstance().startLRUPolicyMonitoring(cacheCollection);
    }

    private void initFIFOMonitoringForCaches(Collection<JavaCache<? super Serializable,?>> cacheCollection){
        DaemonFactory.getInstance().startFIFOPolicyMonitoring(cacheCollection);
    }

    private void initLFUMonitoringForCaches(Collection<JavaCache<? super Serializable,?>> cacheCollection){
        DaemonFactory.getInstance().startLFUPolicyMonitoring(cacheCollection);
    }

    private void initRRMonitoringForCaches(Collection<JavaCache<? super Serializable,?>> cacheCollection){
        DaemonFactory.getInstance().startRRPolicyMonitoring(cacheCollection);
    }

    private void initSizeBasedEvictionMonitoringForCaches(Collection<JavaCache<? super Serializable,?>> cacheCollection){
        DaemonFactory.getInstance().startSizeBasedEvictionPolicyMonitoring(cacheCollection);
    }

}
