package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    static JavaCache<String, String> lruCache;

    @BeforeEach
    void setUp(){
        lruCache = new JavaCache<>("lru");
    }

    @AfterEach
    void clean(){
        lruCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

    //LRU Specific Tests
    @Test
    void lruRemovalAfterMaxSizeReached_SizeTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertEquals(10, lruCache.size());
    }

    @Test
    void lruRemovalAfterMaxSizeReached_GetTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertNull(lruCache.get("key0"));
        assertNull(lruCache.get("key1"));
        assertEquals("value", lruCache.get("key2"));
    }

    @Test
    void lruRemovalAfterMaxSizeReached_KeysTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertTrue(lruCache.keys().contains("key3"));
        assertTrue(lruCache.keys().contains("key10"));
        assertFalse(lruCache.keys().contains("key0"));
        assertFalse(lruCache.keys().contains("key1"));
    }

    @Test
    void lruRemovalAfterMaxSizeReached_RemoveTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertFalse(lruCache.remove("key0"));
        assertFalse(lruCache.remove("key1"));
        assertTrue(lruCache.remove("key3"));
        assertTrue(lruCache.remove("key10"));
        assertFalse(lruCache.keys().contains("key3"));
        assertFalse(lruCache.keys().contains("key10"));
    }

    @Test
    void lruRemovalWithGetUpdate_SizeTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        lruCache.get("key0");
        lruCache.get("key1");
        lruCache.put("key10", "value");
        lruCache.put("key11", "value");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertEquals(10, lruCache.size());
    }

    @Test
    void lruRemovalWithGetUpdate_GetTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        lruCache.get("key0");
        lruCache.get("key1");
        lruCache.put("key10", "value");
        lruCache.put("key11", "value");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertNotNull(lruCache.get("key0"));
        assertNotNull(lruCache.get("key1"));
        assertNull(lruCache.get("key2"));
        assertNull(lruCache.get("key3"));
        assertEquals("value", lruCache.get("key1"));
        assertEquals("value", lruCache.get("key0"));
    }

    @Test
    void lruRemovalWithGetUpdate_KeysTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        lruCache.get("key0");
        lruCache.get("key1");
        lruCache.put("key10", "value");
        lruCache.put("key11", "value");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertTrue(lruCache.keys().contains("key0"));
        assertTrue(lruCache.keys().contains("key1"));
        assertTrue(lruCache.keys().contains("key10"));
        assertFalse(lruCache.keys().contains("key2"));
        assertFalse(lruCache.keys().contains("key3"));
    }

    @Test
    void lruRemovalWithGetUpdate_RemoveTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lruCache.put("key".concat(String.valueOf(i)), "value");
        }
        lruCache.get("key0");
        lruCache.get("key1");
        lruCache.put("key10", "value");
        lruCache.put("key11", "value");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertTrue(lruCache.remove("key0"));
        assertFalse(lruCache.keys().contains("key0"));
        assertTrue(lruCache.remove("key1"));
        assertFalse(lruCache.keys().contains("key1"));
        assertTrue(lruCache.remove("key10"));
        assertFalse(lruCache.keys().contains("key10"));
        assertFalse(lruCache.keys().contains("key2"));
        assertFalse(lruCache.keys().contains("key3"));
    }
}
