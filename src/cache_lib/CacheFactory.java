package cache_lib;

import java.util.Collection;

public class CacheFactory {

    // This factory maintains a collection of JavaObjects with Certain Policy
    /*
    * */

    private void initTTLMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().startTTLPolicyMonitoring(cacheCollection);
    }

    private void initLRUMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().startLRUPolicyMonitoring(cacheCollection);
    }

    private void initFIFOMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().startFIFOPolicyMonitoring(cacheCollection);
    }

    private void initLFUMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().startLFUPolicyMonitoring(cacheCollection);
    }

    private void initRRMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().startRRPolicyMonitoring(cacheCollection);
    }

    private void initSizeBasedEvictionMonitoringForCaches(Collection<JavaCache<?,?>> cacheCollection){
        DaemonFactory.getInstance().startSizeBasedEvictionPolicyMonitoring(cacheCollection);
    }

}
