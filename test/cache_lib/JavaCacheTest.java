package cache_lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    }

    @Test
    public void getExistingKeyTest(){

    }

    @Test
    public void getNullKeyTest(){

    }

    @Test
    public void getResetTTLTest(){

    }

    @Test
    public void removeNonExistingKeyTest(){

    }

    @Test
    public void removeNullKeyTest(){

    }

    @Test
    public void removeExistingKeyTest(){

    }

    @Test
    public void removeExpiredKeyTest(){

    }

    @Test
    public void sizeTestWithNoElements(){

    }

    @Test
    public void sizeTest(){

    }

    @Test
    public void sizeBeforeTTLExpirationTest(){

    }

    @Test
    public void sizeAfterTTLExpirationTest(){

    }

    @Test
    public void clearTest(){

    }

    @Test
    public void keysTestWithNoElements(){

    }

    @Test
    public void keysTest(){

    }
}
