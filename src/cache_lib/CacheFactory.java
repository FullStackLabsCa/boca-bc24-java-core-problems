package cache_lib;

import java.io.Serializable;
import java.util.Collection;

public class CacheFactory {

    // This factory maintains a collection of JavaObjects with Certain Policy
    /*
    * */

    /**
     * it create a given cache object
     * also maintain the cache object references
     *
     * Dameon can get list of all caches managed/maintain and perform the house cleaning
     *
     */

    /**
     *  factory of creating different tyoe of cache
     *  - TTL
     *  - LRU
     *  - LFU
     *
     */
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
