package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {

    static JavaCache<String, String> lfuCache;

    @BeforeEach
    void setUp(){
        lfuCache = new JavaCache<>("lfu");
    }

    @AfterEach
    void clean(){
        lfuCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

    //LFU Tests
    @Test
    void lfuSizeTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lfuCache.put("key".concat(String.valueOf(i)), "value");
        }
        for (int i = 0; i <= 8; i++) {
            lfuCache.get("key".concat(String.valueOf(i)));
        }
        lfuCache.put("key10", "value");
        lfuCache.put("key11", "value");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertEquals(10, lfuCache.size());
    }

    @Test
    void lfuGetTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lfuCache.put("key".concat(String.valueOf(i)), "value");
        }
        for (int i = 0; i <= 8; i++) {
            lfuCache.get("key".concat(String.valueOf(i)));
        }
        lfuCache.put("key10", "value");
        lfuCache.put("key11", "value");
        lfuCache.get("key11");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        for (int i = 0; i <= 8; i++) {
            assertNotNull(lfuCache.get("key".concat(String.valueOf(i))));
        }
        assertNull(lfuCache.get("key9"));
        assertNull(lfuCache.get("key10"));
        for (int i = 0; i <= 8; i++) {
            assertEquals("value", lfuCache.get("key".concat(String.valueOf(i))));
        }
        assertEquals("value", lfuCache.get("key11"));
    }

    @Test
    void lfuKeysTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lfuCache.put("key".concat(String.valueOf(i)), "value");
        }
        for (int i = 0; i <= 8; i++) {
            lfuCache.get("key".concat(String.valueOf(i)));
        }
        lfuCache.put("key10", "value");
        lfuCache.put("key11", "value");
        lfuCache.get("key11");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        for (int i = 0; i <= 8; i++) {
            assertTrue(lfuCache.keys().contains("key".concat(String.valueOf(i))));
        }
        assertTrue(lfuCache.keys().contains("key11"));
        assertFalse(lfuCache.keys().contains("key9"));
        assertFalse(lfuCache.keys().contains("key10"));
    }

    @Test
    void lfuRemoveTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 9; i++) {
            lfuCache.put("key".concat(String.valueOf(i)), "value");
        }
        for (int i = 0; i <= 8; i++) {
            lfuCache.get("key".concat(String.valueOf(i)));
        }
        lfuCache.put("key10", "value");
        lfuCache.put("key11", "value");
        lfuCache.get("key11");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        for (int i = 0; i <= 8; i++) {
            assertTrue(lfuCache.remove("key".concat(String.valueOf(i))));
        }
        for (int i = 0; i <= 8; i++) {
            assertFalse(lfuCache.keys().contains("key".concat(String.valueOf(i))));
        }
        assertFalse(lfuCache.remove("key9"));
        assertFalse(lfuCache.remove("key10"));
    }
}
