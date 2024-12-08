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

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putNullKeyTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put(null, "value");
        assertEquals(0, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putNullValueTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key1", null);
        assertEquals(1, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putNullKeyAndValueTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put(null, null);
        assertEquals(0, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putNullValueMultipleTimesTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key1", null);
        assertEquals(1, javaCache.size());
        javaCache.put("key2", null);
        assertEquals(2, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putSameKeyMultipleTimesTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key1", "value1");
        assertEquals(1, javaCache.size());
        javaCache.put("key1", "value2");
        assertEquals(1, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void getNonExistentKeyTest(JavaCache<String, String> javaCache){
        assertNull(javaCache.get("nonExistentKey"));
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void getExistingKeyTest(JavaCache<String, String> javaCache){
        javaCache.put("key", "value");
        assertEquals("value", javaCache.get("key"));
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void getNullKeyTest(JavaCache<String, String> javaCache){
        assertNull(javaCache.get(null));
    }

    @Test
    void getResetTTLTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        assertEquals("value", ttlCache.get("key"));
        Thread.sleep(2000);
        assertNull(ttlCache.get("key"));
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void removeNonExistingKeyTest(JavaCache<String, String> javaCache){
        assertFalse(javaCache.remove("nonExistingKey"));
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void removeNullKeyTest(JavaCache<String, String> javaCache){
        assertFalse(javaCache.remove(null));
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void removeExistingKeyTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key", "value");
        assertEquals(1, javaCache.size());

        assertTrue(javaCache.remove("key"));
        assertEquals(0, javaCache.size());
    }

    @Test
    void removeExpiredKeyTest() throws InterruptedException {
        ttlCache.put("key", "value", 1);
        Thread.sleep(2000);
        assertFalse(ttlCache.remove("key"));
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void sizeTestWithNoElements(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void sizeTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key", "value");
        javaCache.put("key1", "value");
        assertEquals(2, javaCache.size());
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

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void clearTest(JavaCache<String, String> javaCache){
        javaCache.put("key", "value");
        javaCache.put("key1", "value");
        javaCache.put("key2", "value");

        assertEquals(3, javaCache.size());
        javaCache.clear();
        assertEquals(0, javaCache.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void keysTestWithNoElements(JavaCache<String, String> javaCache){
        Set<String> keys = javaCache.keys();
        assertEquals(0, keys.size());
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void keysTest(JavaCache<String, String> javaCache){
        javaCache.put("key", "value");
        javaCache.put("key1", "value");

        Set<String> keys = javaCache.keys();
        assertEquals(2, keys.size());

        assertTrue(keys.contains("key"));
        assertTrue(keys.contains("key1"));
        assertFalse(keys.contains("key2"));
    }
}
