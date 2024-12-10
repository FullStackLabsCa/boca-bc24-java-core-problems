package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class JavaCacheTest {

    @AfterEach
    void clean(){
        CacheManager.getInstance().resetCacheManager();
    }

    static Stream<Arguments> cacheProvider(){
        JavaCache<String, String> localTtlCache = new JavaCache<>("ttl");
        JavaCache<String, String> localLruCache = new JavaCache<>("lru");
        JavaCache<String, String> localLfuCache = new JavaCache<>("lfu");
        return Stream.of(
                Arguments.of(localTtlCache),
                Arguments.of(localLruCache),
                Arguments.of(localLfuCache)
        );
    }

    @ParameterizedTest
    @MethodSource("cacheProvider")
    void putTest(JavaCache<String, String> javaCache){
        assertEquals(0, javaCache.size());
        javaCache.put("key", "value");
        assertEquals(1, javaCache.size());
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
