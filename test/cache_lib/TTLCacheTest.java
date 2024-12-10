package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TTLCacheTest {

    static JavaCache<String, String> ttlCache;

    @BeforeEach
    void setUp(){
        ttlCache = new JavaCache<>("ttl");
    }

    @AfterEach
    void clean(){
        ttlCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

//TTL Specific Tests
    @Test
    void putWithDurationTest_TTL(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value",10);
        assertEquals(1, ttlCache.size());
    }

    @Test
    void putTestRemovalAfterTTL() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        assertEquals(1, ttlCache.size());
        Thread.sleep(12000);
        assertEquals(0, ttlCache.size());
    }

    @Test
    void putWithDurationTestRemovalAfterTTL() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value",1);
        assertEquals(1, ttlCache.size());
        Thread.sleep(3000);
        assertEquals(0, ttlCache.size());
    }

    @Test
    void TTLResetAfterReadTest() throws InterruptedException {
        ttlCache.put("key", "value", 2);
        Thread.sleep(1000);
        ttlCache.get("key");
        Thread.sleep(1000);
        assertEquals(1, ttlCache.size());
    }

    @Test
    void getResetTTLTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        assertEquals("value", ttlCache.get("key"));
        Thread.sleep(3000);
        assertNull(ttlCache.get("key"));
    }

    @Test
    void removeTTLExpiredKeyTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        Thread.sleep(2000);
        assertFalse(ttlCache.remove("key"));
    }

    @Test
    void sizeBeforeTTLExpirationTest() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value", 2);
        Thread.sleep(1000);
        assertEquals(1, ttlCache.size());
    }

    @Test
    void sizeAfterTTLExpirationTest() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value", 2);
        Thread.sleep(4000);
        assertEquals(0, ttlCache.size());
    }
}
