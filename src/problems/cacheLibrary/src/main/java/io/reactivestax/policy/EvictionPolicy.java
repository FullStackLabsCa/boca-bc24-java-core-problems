package io.reactivestax.policy;

import io.reactivestax.service.Cache;

public interface EvictionPolicy<K, V> {
    void eviction(Cache<K, V> cache);

/*    default void    startEvictionThread(Cache<K, V> cache) {
        Thread evictionThread = new Thread(() -> {
            while (true) {
                try {
                    eviction(cache);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        evictionThread.setDaemon(true);
        evictionThread.setName("EvictionThread-" + Thread.currentThread().getName());
        evictionThread.start();
    }*/
}
