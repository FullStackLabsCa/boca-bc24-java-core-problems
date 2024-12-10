//package io.reactivestax.service;
//
//import io.reactivestax.policy.TTL;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class CacheTest {
//    Cache<Integer, String> cacheMap;
//
//    @BeforeEach
//    void setUp() {
//        cacheMap = new Cache<>(new TTL<>());
//    }
//
//    @Test
//    void testPut() {
//        cacheMap.put(1, "Value 1");
//        String value = cacheMap.getById(1).value;
//        int cacheMapSize = cacheMap.getSizeofCache();
//
//        assertEquals("Value 1", value);
//        assertEquals(1, cacheMapSize);
//    }
//
//    @Test
//    void testGetById() {
//
//    }
//
//    @Test
//    void testRemoveById() {
//        cacheMap.put(1, "Value 1");
//        boolean isRemoved = cacheMap.removeById(1);
//
//        assertTrue(isRemoved);
//    }
//
//    @Test
//    void testClearCache() {
//        cacheMap.put(1, "Value 1");
//        cacheMap.clearCache();
//
//        int sizeOfCache = cacheMap.getSizeofCache();
//        assertEquals(0, sizeOfCache);
//    }
//
//    @Test
//    void testGetAllKeys() {
//        cacheMap.put(1, "Value 1");
//        cacheMap.put(2, "Value 2");
//        cacheMap.put(3, "Value 3");
//        Set<Integer> keySet = cacheMap.getAllKeys();
//
//        assertEquals("[1, 2, 3]", keySet.toString());
//    }
//}