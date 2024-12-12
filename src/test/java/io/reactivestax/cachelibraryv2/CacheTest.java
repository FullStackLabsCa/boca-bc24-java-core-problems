package io.reactivestax.cachelibraryv2;

import io.reactivestax.cachelibraryv1.CacheMain;
import org.junit.Test;
import static org.junit.Assert.*;

public class CacheTest {


    @Test
    public void testFIFOEvictionPolicy() {
    CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("FIFO", 3);
    assertNotNull(cache);
    cache.putKeyValueDefaultTtl(1, "A");
    cache.putKeyValueDefaultTtl(2, "B");
    cache.putKeyValueDefaultTtl(3, "C");
    cache.putKeyValueDefaultTtl(4, "D");
    assertNull(cache.getValue(1));
    assertTrue(cache.getValue(4), true);
    }


    @Test
    public void testLRUEvictionPolicy(){
        CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("LRU", 3);
        assertNotNull(cache);
        cache.putKeyValueDefaultTtl(1, "A");
        cache.putKeyValueDefaultTtl(2, "B");
        cache.putKeyValueDefaultTtl(3, "C");
        cache.getValue(2);
        cache.putKeyValueDefaultTtl(4, "D");
        assertNull(cache.getValue(1));
        assertTrue(cache.getValue(4), true);

    }


    @Test
    public void testLFUEvictionPolicy(){
        CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("LFU", 3);
        assertNotNull(cache);
        cache.putKeyValueDefaultTtl(1, "A");
        cache.putKeyValueDefaultTtl(2, "B");
        cache.putKeyValueDefaultTtl(3, "C");
        cache.getValue(2);
        cache.getValue(2);
        cache.getValue(1);
        cache.putKeyValueDefaultTtl(4, "D");
        assertNull(cache.getValue(3));
        assertTrue(cache.getValue(4), true);

    }

    @Test
    public void testLFUEvictionPolicyWithSameFrequency(){
        CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("LFU", 3);
        assertNotNull(cache);
        cache.putKeyValueDefaultTtl(1, "A");
        cache.putKeyValueDefaultTtl(2, "B");
        cache.putKeyValueDefaultTtl(3, "C");
        cache.getValue(2);
        cache.getValue(1);
        cache.getValue(3);
        cache.putKeyValueDefaultTtl(4, "D");
        assertNull(cache.getValue(1));
        assertTrue(cache.getValue(4), true);
    }


    @Test
    public void testRandomEvictionPolicy(){
        CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("RANDOM", 3);
        assertNotNull(cache);
        cache.putKeyValueDefaultTtl(1, "A");
        cache.putKeyValueDefaultTtl(2, "B");
        cache.putKeyValueDefaultTtl(3, "C");
        cache.putKeyValueDefaultTtl(4, "D");
        assertTrue(cache.getValue(4), true);
    }

    @Test
    public void testTTLEvictionPolicy() throws InterruptedException {
        TTLEvictionPolicy evictionPolicy = new TTLEvictionPolicy();
        CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("TTL", 4);
        assertNotNull(cache);
        cache.putKeyValueDefaultTtl(1, "A");
        cache.putKeyValueDefaultTtl(2, "B");
        cache.putKeyValueDefaultTtl(3, "C");
        cache.putKeyValueTTL(4, "D", 4);
        cache.putKeyValueTTL(5, "E", -1);
        evictionPolicy.startDaemonThread(cache);
        Thread.sleep(7000);
        assertTrue(cache.getValue(2), true);
        assertTrue(cache.getValue(3), true);
        assertTrue(cache.getValue(5), true);
        assertNull(cache.getValue(4));
    }



    @Test
    public void testDaemonThreadInterruption() throws InterruptedException {
        TTLEvictionPolicy evictionPolicy = new TTLEvictionPolicy();
        CacheLibrary<Integer, String> cache = EvictionPolicyFactory.createCache("TTL", 4);
        cache.putKeyValueDefaultTtl(1, "test value");
        evictionPolicy.startDaemonThread(cache);
        Thread.sleep(3000);
        evictionPolicy.startDaemonThread(cache).interrupt();
        Thread.sleep(1000);
        assertNotNull(cache.getValue(1));
    }
}