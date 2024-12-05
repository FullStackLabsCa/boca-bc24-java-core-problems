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
    public void putTestWithDuration(){
        assertEquals(0, cache.size());
        cache.put("akshat", "singla",10);
        assertEquals(1, cache.size());
    }



}
