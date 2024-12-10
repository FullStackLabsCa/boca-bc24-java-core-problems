package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FIFOCacheTest {

    static JavaCache<String, String> fifoCache;

    @BeforeEach
    void setUp(){
        fifoCache = new JavaCache<>("fifo");
    }

    @AfterEach
    void clean(){
        fifoCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

    //FIFO Tests
    @Test
    void fifoSizeTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            fifoCache.put("key".concat(String.valueOf(i)), "value");
        }
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertEquals(10, fifoCache.size());
    }

    @Test
    void fifoGetTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            fifoCache.put("key".concat(String.valueOf(i)), "value");
        }
        fifoCache.get("key1");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        for (int i = 2; i <= 11; i++) {
            assertNotNull(fifoCache.get("key".concat(String.valueOf(i))));
        }
        assertNull(fifoCache.get("key0"));
        assertNull(fifoCache.get("key1"));
        for (int i = 2; i <= 11; i++) {
            assertEquals("value", fifoCache.get("key".concat(String.valueOf(i))));
        }
    }

    @Test
    void fifoKeysTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            fifoCache.put("key".concat(String.valueOf(i)), "value");
        }
        fifoCache.get("key1");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        for (int i = 2; i <= 11; i++) {
            assertTrue(fifoCache.keys().contains("key".concat(String.valueOf(i))));
        }
        assertFalse(fifoCache.keys().contains("key0"));
        assertFalse(fifoCache.keys().contains("key1"));
    }

    @Test
    void fifoRemoveTest() throws InterruptedException {
        //SetUp
        for (int i = 0; i <= 11; i++) {
            fifoCache.put("key".concat(String.valueOf(i)), "value");
        }
        fifoCache.get("key1");
        fifoCache.get("key0");
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        for (int i = 2; i <= 11; i++) {
            assertTrue(fifoCache.remove("key".concat(String.valueOf(i))));
        }

        for (int i = 0; i <= 11; i++) {
            assertNull(fifoCache.get("key".concat(String.valueOf(i))));
        }
    }
}
