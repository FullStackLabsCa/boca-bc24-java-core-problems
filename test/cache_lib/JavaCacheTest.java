package cache_lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JavaCacheTest {

    JavaCache<String, String> ttlCache;

    @Before
    public void setUp(){
        ttlCache = new JavaCache<>("ttl");
    }

    @After
    public void clean(){
        ttlCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

    @Test
    public void putTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        assertEquals(1, ttlCache.size());
    }

    @Test
    public void putWithDurationTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value",10);
        assertEquals(1, ttlCache.size());
    }

    @Test
    public void putTestRemovalAfterTTL() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        assertEquals(1, ttlCache.size());
        Thread.sleep(6000);
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void putWithDurationTestRemovalAfterTTL() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value",1);
        assertEquals(1, ttlCache.size());
        Thread.sleep(3000);
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void putTestTTLResetAfterRead(){
        //TODO
    }

    @Test
    public void putNullKeyTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put(null, "value");
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void putNullValueTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key1", null);
        assertEquals(1, ttlCache.size());
    }

    @Test
    public void putNullKeyAndValueTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put(null, null);
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void putNullValueMultipleTimesTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key1", null);
        assertEquals(1, ttlCache.size());
        ttlCache.put("key2", null);
        assertEquals(2, ttlCache.size());
    }

    @Test
    public void putSameKeyMultipleTimesTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key1", "value1");
        assertEquals(1, ttlCache.size());
        ttlCache.put("key1", "value2");
        assertEquals(1, ttlCache.size());
    }

    @Test
    public void getNonExistentKeyTest(){
        assertNull(ttlCache.get("nonExistentKey"));
    }

    @Test
    public void getExistingKeyTest(){
        ttlCache.put("key", "value");
        assertEquals("value", ttlCache.get("key"));
    }

    @Test
    public void getNullKeyTest(){
        assertNull(ttlCache.get(null));
    }

    @Test
    public void getResetTTLTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        assertEquals("value", ttlCache.get("key"));
        Thread.sleep(2000);
        assertNull(ttlCache.get("key"));
    }

    @Test
    public void removeNonExistingKeyTest(){
        assertFalse(ttlCache.remove("nonExistingKey"));
    }

    @Test
    public void removeNullKeyTest(){
        assertFalse(ttlCache.remove(null));
    }

    @Test
    public void removeExistingKeyTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        assertEquals(1, ttlCache.size());

        assertTrue(ttlCache.remove("key"));
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void removeExpiredKeyTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        Thread.sleep(2000);
        assertFalse(ttlCache.remove("key"));
    }

    @Test
    public void sizeTestWithNoElements(){
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void sizeTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        ttlCache.put("key1", "value");
        assertEquals(2, ttlCache.size());
    }

    @Test
    public void sizeBeforeTTLExpirationTest() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value", 2);
        Thread.sleep(1000);
        assertEquals(1, ttlCache.size());
    }

    @Test
    public void sizeAfterTTLExpirationTest() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value", 2);
        Thread.sleep(3000);
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void clearTest(){
        ttlCache.put("key", "value");
        ttlCache.put("key1", "value");
        ttlCache.put("key2", "value");

        assertEquals(3, ttlCache.size());
        ttlCache.clear();
        assertEquals(0, ttlCache.size());
    }

    @Test
    public void keysTestWithNoElements(){

    }

    @Test
    public void keysTest(){

    }
}
