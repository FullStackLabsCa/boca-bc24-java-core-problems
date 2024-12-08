package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class JavaCacheTest {

    static JavaCache<String, String> ttlCache;
    static JavaCache<String, String> lruCache;

    @BeforeEach
    void setUp(){
        ttlCache = new JavaCache<>("ttl");
        lruCache = new JavaCache<>("lru");
    }

    @AfterEach
    void clean(){
        ttlCache.clear();
        lruCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

    static Stream<Arguments> cacheProvider(){
        JavaCache<String, String> localTtlCache = new JavaCache<>("ttl");
        JavaCache<String, String> localLruCache = new JavaCache<>("lru");
        return Stream.of(
                Arguments.of(localTtlCache),
                Arguments.of(localLruCache)
        );
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key", "value");
        assertEquals(1, javaCache.size());
    }

    @Test
    void putWithDurationTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value",10);
        assertEquals(1, ttlCache.size());
    }

    @Test
    void putTestRemovalAfterTTL() throws InterruptedException {
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        assertEquals(1, ttlCache.size());
        Thread.sleep(6000);
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
    void putTestTTLResetAfterRead(){
        //TODO
    }

    @Test
    void putNullKeyTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put(null, "value");
        assertEquals(0, ttlCache.size());
    }

    @Test
    void putNullValueTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key1", null);
        assertEquals(1, ttlCache.size());
    }

    @Test
    void putNullKeyAndValueTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put(null, null);
        assertEquals(0, ttlCache.size());
    }

    @Test
    void putNullValueMultipleTimesTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key1", null);
        assertEquals(1, ttlCache.size());
        ttlCache.put("key2", null);
        assertEquals(2, ttlCache.size());
    }

    @Test
    void putSameKeyMultipleTimesTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key1", "value1");
        assertEquals(1, ttlCache.size());
        ttlCache.put("key1", "value2");
        assertEquals(1, ttlCache.size());
    }

    @Test
    void getNonExistentKeyTest(){
        assertNull(ttlCache.get("nonExistentKey"));
    }

    @Test
    void getExistingKeyTest(){
        ttlCache.put("key", "value");
        assertEquals("value", ttlCache.get("key"));
    }

    @Test
    void getNullKeyTest(){
        assertNull(ttlCache.get(null));
    }

    @Test
    void getResetTTLTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        assertEquals("value", ttlCache.get("key"));
        Thread.sleep(2000);
        assertNull(ttlCache.get("key"));
    }

    @Test
    void removeNonExistingKeyTest(){
        assertFalse(ttlCache.remove("nonExistingKey"));
    }

    @Test
    void removeNullKeyTest(){
        assertFalse(ttlCache.remove(null));
    }

    @Test
    void removeExistingKeyTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        assertEquals(1, ttlCache.size());

        assertTrue(ttlCache.remove("key"));
        assertEquals(0, ttlCache.size());
    }

    @Test
    void removeExpiredKeyTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        Thread.sleep(2000);
        assertFalse(ttlCache.remove("key"));
    }

    @Test
    void sizeTestWithNoElements(){
        assertEquals(0, ttlCache.size());
    }

    @Test
    void sizeTest(){
        assertEquals(0, ttlCache.size());
        ttlCache.put("key", "value");
        ttlCache.put("key1", "value");
        assertEquals(2, ttlCache.size());
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
        Thread.sleep(3000);
        assertEquals(0, ttlCache.size());
    }

    @Test
    void clearTest(){
        ttlCache.put("key", "value");
        ttlCache.put("key1", "value");
        ttlCache.put("key2", "value");

        assertEquals(3, ttlCache.size());
        ttlCache.clear();
        assertEquals(0, ttlCache.size());
    }

    @Test
    void keysTestWithNoElements(){
        Set<String> keys = ttlCache.keys();
        assertEquals(0, keys.size());
    }

    @Test
    void keysTest(){
        ttlCache.put("key", "value");
        ttlCache.put("key1", "value");

        Set<String> keys = ttlCache.keys();
        assertEquals(2, keys.size());

        assertTrue(keys.contains("key"));
        assertTrue(keys.contains("key1"));
        assertFalse(keys.contains("key2"));
    }
}
