package cache_lib;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaCacheTest {

    JavaCache<String, String> cache = new JavaCache<>();

    @Before
    public void setUp(){
        cache.clear();
    }

    @Test
    public void putTest(){
        assertEquals(0, cache.size());
        cache.put("akshat", "singla");
        assertEquals(1, cache.size());
    }

    @Test
    public void putWithDurationTest(){
        assertEquals(0, cache.size());
        cache.put("akshat", "singla",10);
        assertEquals(1, cache.size());
    }

    @Test
    public void putTestRemovalAfterTTL(){

    }

    @Test
    public void putWithDurationTestRemovalAfterTTL(){

    }

    @Test
    public void putTestTTLResetAfterRead(){

    }

    @Test
    public void getNonExistentKeyTest(){

    }

    @Test
    public void getExistingKeyTest(){

    }

    @Test
    public void getResetTTLTest(){

    }

    @Test
    public void removeNonExistingKeyTest(){

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
