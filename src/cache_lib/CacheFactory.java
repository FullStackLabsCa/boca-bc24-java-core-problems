package cache_lib;

import java.util.Collection;

public class CacheFactory {

    // This factory maintains a collection of JavaObjects with Certain Policy
    /*
    * */

    private void initTTLMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().ttlPolicyMonitorProvider(cacheCollection);
    }

    private void initLRUMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().LRUPolicyMonitorProvider(cacheCollection);
    }

    private void initFIFOMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().FIFOPolicyMonitorProvider(cacheCollection);
    }

    private void initLFUMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().LFUPolicyMonitorProvider(cacheCollection);
    }

    private void initRRMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().RRPolicyMonitorProvider(cacheCollection);
    }

    private void initSizeBasedEvictionMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().sizeBasedEvictionPolicyMonitorProvider(cacheCollection);
    }

}
