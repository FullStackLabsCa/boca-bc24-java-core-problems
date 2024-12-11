package io.reactivestax.cachelibraryv2;



public class CacheRunner {

    public static Thread startDaemonThread(CacheLibrary<Integer, String> cache) {
        Thread daemonThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Running daemon thread");
                try {
                    Thread.sleep(1000);
                    cache.cleanExpiredValues();
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

    public static void main(String[] args) {

        System.out.println("LRU Eviction Policy ===========");
        CacheLibrary<Integer, String> cacheLRU = EvictionPolicyFactory.createCache("LRU", 3);
        cacheLRU.putKeyValueDefaultTtl(1, "Shifa");
        cacheLRU.putKeyValueDefaultTtl(2, "Manpreet");
        cacheLRU.putKeyValueDefaultTtl(3, "Sukhvir");
        System.out.println("Keys - " + cacheLRU.retrieveKeys());
        cacheLRU.getValue(1);
        cacheLRU.putKeyValueDefaultTtl(4, "RR");
        System.out.println("Keys after adding 4th value" + cacheLRU.retrieveKeys());

        System.out.println("");

        System.out.println("LFU Eviction Policy ===========");
        CacheLibrary<Integer, String> cacheLFU = EvictionPolicyFactory.createCache("LFU", 3);
        cacheLFU.putKeyValueDefaultTtl(1, "Shifa");
        cacheLFU.putKeyValueDefaultTtl(2, "Manpreet");
        cacheLFU.putKeyValueDefaultTtl(3, "Sukhvir");
        System.out.println("Keys - " + cacheLFU.retrieveKeys());
        cacheLFU.getValue(1);
        cacheLFU.getValue(3);
        cacheLFU.getValue(3);
        cacheLFU.putKeyValueDefaultTtl(4, "RR");
        System.out.println("Keys after adding 4th value : " + cacheLFU.retrieveKeys());

        System.out.println("");

        System.out.println("TTL Eviction Policy ===========");
        CacheLibrary<Integer, String> cacheTTL = EvictionPolicyFactory.createCache("TTL", 3);
        cacheTTL.putKeyValueDefaultTtl(1, "Shifa");
        cacheTTL.putKeyValueDefaultTtl(2, "Manpreet");
        cacheTTL.putKeyValueDefaultTtl(3, "Sukhvir");
        cacheTTL.putKeyValueTTL(4, "Kiran", 10);
        cacheTTL.putKeyValueTTL(5, "Richa", -1);
        startDaemonThread(cacheTTL);
        System.out.println("All keys:: " + cacheTTL.retrieveKeys());
        System.out.println("ID 2 is : " + cacheTTL.getValue(2));
        System.out.println("Key removal successful = " + cacheTTL.removeKey(1));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All keys after eviction :: " + cacheTTL.retrieveKeys());
        System.out.println("Current Size of map - " + cacheTTL.getSize());
        cacheTTL.clearCache();
        System.out.println("Cleanup done, size of map is : " + cacheTTL.getSize());

    }
}
