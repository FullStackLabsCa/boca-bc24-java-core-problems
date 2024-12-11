package io.reactivestax.cachelibraryv1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class CacheManagementTest {

    private CacheManagement<Integer, String> cacheManagement;
    private Map<Integer, CacheValue<String>> cacheValueMap;

    @BeforeEach
    void setUp(){
        cacheValueMap = new HashMap<>();
        cacheManagement = new CacheManagement<>(cacheValueMap, 60);

    }

    @Test
    void testPutKeyValue(){
        Integer key = 1;
        String value = "testValue";
        cacheManagement.putKeyValue(key, value);
        String getValue = cacheManagement.getValue(key);
        assertNotNull(getValue);
        assertEquals(value, getValue);
    }

    @Test
    void testRemoveKey(){
        Integer key = 1;
        String value = "testValue";
        cacheManagement.putKeyValue(key, value);
        boolean isRemoved = cacheManagement.removeKey(key);
        assertTrue(isRemoved);
        assertNull(cacheManagement.getValue(key));
    }


    @Test
    void testRemoveKeyForInvalidKey(){
        boolean isRemoved = cacheManagement.removeKey(0);
        assertFalse(isRemoved);
    }


    @Test
    void testCleanUp(){
        cacheManagement.putKeyValue(2, "Abc");
        cacheManagement.putKeyValueTTL(3, "Def", 80);
        cacheManagement.doCleanup();
        assertEquals(0, cacheManagement.getSize());

}


    @Test
    void testPutKeyValueWithCustomTTL(){
        cacheManagement.putKeyValueTTL(1, "Abc", 60);
        cacheManagement.putKeyValueTTL(2, "def", -1);
        String getValueOne = cacheManagement.getValue(1);
        String getValueTwo = cacheManagement.getValue(2);
        assertNotNull(getValueOne);
        assertEquals("Abc", getValueOne);
        assertNotNull(getValueTwo);
        assertEquals("def", getValueTwo);

    }

    @Test
    void testRetrieveKeys(){
        cacheManagement.putKeyValue(1, "A");
        cacheManagement.putKeyValue(2, "B");
        cacheManagement.putKeyValue(3, "C");
      Set<Integer> keySet =  cacheManagement.retrieveKeys();
      assertTrue(keySet.contains(1));
      assertTrue(keySet.contains(2));
      assertTrue(keySet.contains(3));
    }

    @Test
    void testCleanExpiredValues() throws InterruptedException {
        cacheManagement.putKeyValue(1, "A");
        cacheManagement.putKeyValue(2, "B");
        CacheValue<String> cacheValue = cacheValueMap.get(1);
        cacheValue.setTTL(1);
        Thread.sleep(2000);
        cacheManagement.cleanExpiredValues();
        assertNull(cacheManagement.getValue(1));
        assertNotNull(cacheManagement.getValue(2));
    }

    @Test
    void testExpiryForIndefiniteTTL() throws InterruptedException {
        cacheManagement.putKeyValue(1, "A");
        CacheValue<String> cacheValue = cacheValueMap.get(1);
        cacheValue.setTTL(-1);
        Thread.sleep(2000);
        cacheManagement.cleanExpiredValues();
        assertNotNull(cacheManagement.getValue(1));

    }

    @Test
    void testIsExpired() throws InterruptedException {
        CacheValue<String> expiredValue = new CacheValue<>("value", 1);
        TimeUnit.SECONDS.sleep(2);
        assertTrue(expiredValue.isExpired(System.currentTimeMillis()));
    }

    @Test
    void testIsExpiredForNonExpiredTtl(){
        CacheValue<String> expiredValue = new CacheValue<>("value", -1);
        assertFalse(expiredValue.isExpired(System.currentTimeMillis()));
    }

    @Test
    void testDaemonThreadCleanUp() throws InterruptedException {
        cacheManagement.putKeyValue(1, "A");
        cacheManagement.putKeyValue(2, "B");
        cacheManagement.putKeyValueTTL(3, "C", 2);

        CacheMain.startDaemonThread(cacheManagement);
        Thread.sleep(3000);
        assertNotNull(cacheManagement.getValue(1));
        assertNotNull(cacheManagement.getValue(2));
        assertNull(cacheManagement.getValue(3));

    }

    @Test
    void testDaemonThreadInterruption() throws InterruptedException {
    cacheManagement.putKeyValue(1, "test value");
    Thread daemonThread = CacheMain.startDaemonThread(cacheManagement);
    Thread.sleep(3000);
    daemonThread.interrupt();
    Thread.sleep(1000);
    assertNotNull(cacheManagement.getValue(1));
    }


    @Test
    void testRunnerFunction() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String[] args = {};
        CacheMain.main(args);
        String output = outputStream.toString();
        assertTrue(output.contains("All keys::"));
        assertTrue(output.contains("ID 2 is : Manpreet"));
        assertTrue(output.contains("Key removal successful = true"));
        assertTrue(output.contains("Cleanup done, size of map is"));
    }
}