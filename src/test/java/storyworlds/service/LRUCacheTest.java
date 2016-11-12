package storyworlds.service;

import java.util.UUID;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 10/30/2016.
 */
public class LRUCacheTest {
    private Logger logr = LoggerFactory.getLogger(getClass());

    @Test
    public void testLRUCache() {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.putIfAbsent("one", 1);
        cache.putIfAbsent("two", 2);
        cache.putIfAbsent("three", 3);
        cache.putIfAbsent("four", 4);
        assertFalse("lruCache should remove elements in oldest order",cache.containsKey("one"));
        assertTrue("lruCache should contain elements up to capacity", cache.containsKey("two"));
    }

    @Test
    @Ignore // not really a test
    public void concurrencyTest() {
        LRUCache<String, Integer> cache = new LRUCache<>(10);

        for (int i = 0; i < 2000; ++i) {
            CacheFlood flooder = new CacheFlood(cache);
            flooder.run();
        }
    }

    private class CacheFlood implements Runnable {

        private Logger logr = LoggerFactory.getLogger(getClass());

        private final LRUCache lruCache;

        public CacheFlood(LRUCache cacheToFlood) {
            this.lruCache = cacheToFlood;
        }

        public void run() {
            for (int i = 0; i < 20; ++i) {
                String id = UUID.randomUUID().toString();
                lruCache.putIfAbsent(UUID.randomUUID(), i);
                logr.info("Put " + id);
            }
        }
    }
}
