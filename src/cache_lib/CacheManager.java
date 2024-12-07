package cache_lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CacheManager {

    private static CacheManager instance;
    private static Collection<JavaCache<? super Serializable,?>> ttlCaches;
    private static Collection<JavaCache<? super Serializable,?>> lruCaches;
    private static Collection<JavaCache<? super Serializable,?>> fifoCaches;
    private static Collection<JavaCache<? super Serializable,?>> lfuCaches;
    private static Collection<JavaCache<? super Serializable,?>> rrCaches;
    private static Collection<JavaCache<? super Serializable,?>> sizeBasedEvictionCaches;

    public static synchronized CacheManager getInstance() {
        if (instance == null) instance = new CacheManager();
        return instance;
    }

    public void initCacheManagement(JavaCache<Serializable, ?> javaCache){
        String policy = javaCache.getEvictionPolicy();
        Collection<JavaCache<? super Serializable,?>> cacheCollection = checkIfInitialized(policy);
        if(cacheCollection.isEmpty()) cacheCollection.add(javaCache);
        else {
            Collection<JavaCache<? super Serializable, ?>> javaCaches = initPolicy(policy);
            javaCaches.add(javaCache);
        }
    }

    private Collection<JavaCache<? super Serializable,?>> initPolicy(String policy) {
        return switch (policy) {
            case "ttl" -> initTTLMonitoringForCaches();
            case "lru" -> initLRUMonitoringForCaches();
            case "lfu" -> initLFUMonitoringForCaches();
            case "fifo" -> initFIFOMonitoringForCaches();
            case "rr" -> initRRMonitoringForCaches();
            case "sizeBased" -> initSizeBasedEvictionMonitoringForCaches();
            default -> throw new RuntimeException("Invalid Eviction Policy Mentioned!!!");
        };
    }

    private Collection<JavaCache<? super Serializable, ?>> checkIfInitialized(String policy) {
        switch (policy) {
            case "ttl":
                if(ttlCaches!=null) return ttlCaches;
                break;
            case "lru":
                if(lruCaches!=null) return lruCaches;
                break;
            case "lfu":
                if(lfuCaches!=null) return lfuCaches;
                break;
            case "fifo":
                if(fifoCaches!=null) return fifoCaches;
                break;
            case "rr":
                if(rrCaches!=null) return rrCaches;
                break;
            case "sizeBased":
                if(sizeBasedEvictionCaches!=null) return sizeBasedEvictionCaches;
                break;
            default:
                return Collections.emptyList();
        }
        return Collections.emptyList();
    }


    private static Collection<JavaCache<? super Serializable,?>> initTTLMonitoringForCaches(){
        ttlCaches = new ArrayList<>();
        DaemonFactory.getInstance().startTTLPolicyMonitoring(ttlCaches);
        return ttlCaches;
    }

    private static Collection<JavaCache<? super Serializable,?>> initLRUMonitoringForCaches(){
        lruCaches = new ArrayList<>();
        DaemonFactory.getInstance().startLRUPolicyMonitoring(lruCaches);
        return lruCaches;
    }

    private static Collection<JavaCache<? super Serializable,?>> initFIFOMonitoringForCaches(){
        fifoCaches = new ArrayList<>();
        DaemonFactory.getInstance().startFIFOPolicyMonitoring(fifoCaches);
        return fifoCaches;
    }

    private static Collection<JavaCache<? super Serializable,?>> initLFUMonitoringForCaches(){
        lfuCaches = new ArrayList<>();
        DaemonFactory.getInstance().startLFUPolicyMonitoring(lfuCaches);
        return lfuCaches;
    }

    private static Collection<JavaCache<? super Serializable,?>> initRRMonitoringForCaches(){
        rrCaches = new ArrayList<>();
        DaemonFactory.getInstance().startRRPolicyMonitoring(rrCaches);
        return rrCaches;
    }

    private static Collection<JavaCache<? super Serializable,?>> initSizeBasedEvictionMonitoringForCaches(){
        sizeBasedEvictionCaches = new ArrayList<>();
        DaemonFactory.getInstance().startSizeBasedEvictionPolicyMonitoring(sizeBasedEvictionCaches);
        return sizeBasedEvictionCaches;
    }

}
