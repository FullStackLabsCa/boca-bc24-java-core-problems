package io.reactivestax.cachelibraryv2;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TTLEvictionPolicy<K,V> implements EvictionPolicy<K,V>{
    private final Map<K, CacheEntry<K, V>> cacheEntryMap;

    public TTLEvictionPolicy() {
        this.cacheEntryMap = new ConcurrentHashMap<>();
    }

    @Override
    public void evict(Map<K, CacheEntry<K, V>> cacheMap) {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<K, CacheEntry<K,V>>> cacheIterator = cacheMap.entrySet().iterator();
        while(cacheIterator.hasNext()){
            Map.Entry<K, CacheEntry<K,V>> entry = cacheIterator.next();
            CacheEntry<K,V> cacheEntry = entry.getValue();
            if(cacheEntry.isExpired(currentTime)){
                cacheIterator.remove();
            }
        }
    }

    public Thread startDaemonThread(CacheLibrary<Integer, String> cache) {
        Thread daemonThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    cache.applyEvictionPolicy();
                } catch (InterruptedException e) {
                    System.out.println("Daemon thread interrupted");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
        return daemonThread;
    }
}
