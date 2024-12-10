package cache_lib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RRCacheTest {

    static JavaCache<String, String> rrCache;

    @BeforeEach
    void setUp(){
        rrCache = new JavaCache<>("rr");
    }

    @AfterEach
    void clean(){
        rrCache.clear();
        CacheManager.getInstance().resetCacheManager();
    }

    //RR Tests
    @ParameterizedTest
    @MethodSource("dataInCache")
    void rrSizeTest(int numberOfItems) throws InterruptedException {
        //SetUp
        for (int i = 0; i <= numberOfItems; i++) {
            rrCache.put("key".concat(String.valueOf(i)), "value");
        }
        Thread.sleep(1000); // Allow Daemon Thread to iterate through

        //Assertions
        assertEquals(10, rrCache.size());
    }

    static Stream<Arguments> dataInCache(){
        return Stream.of(
                Arguments.of(13),
                Arguments.of(30),
                Arguments.of(100)
        );
    }
}
