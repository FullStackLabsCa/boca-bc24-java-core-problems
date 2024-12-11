package io.reactivestax.cachelibraryv2;

public class DaemonThread extends Thread {
    private final CacheLibrary<?, ?> cache;

    private DaemonThread(CacheLibrary<?, ?> cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                cache.applyEvictionPolicy();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
