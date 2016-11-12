package storyworlds.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nvaughan on 10/30/2016.
 */
public class LRUCache<K, V> {

    private Logger logr = LoggerFactory.getLogger(getClass());

    private Map<K, V> hashMap;
    private Queue<K> lruEnforcer;
    private final Integer count;
    private final ReentrantLock lock;

    public LRUCache(Integer count) {
        this.count = count;
        lruEnforcer = new LinkedList<K>();
        hashMap = new HashMap<K, V>(count + 2, 1.0F);
        // fairness seems to have a significant performance cost
        lock = new ReentrantLock(false);
    }

    public boolean containsKey(Object key) {
        lock.lock();
        try {
            return hashMap.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    public V get(K key) {
        lock.lock();
        try {
            if (hashMap.containsKey(key)) {
                V value = hashMap.get(key);
                lruEnforcer.remove(key);
                lruEnforcer.add(key);
                return value;
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public V putIfAbsent(K key, V value) {
        if (key == null || value == null) {
            return null;
        }
        lock.lock();
        try {
            if (hashMap.containsKey(key)) {
                lruEnforcer.remove(key);
                lruEnforcer.add(key);
            } else {
                hashMap.put(key, value);
                lruEnforcer.add(key);
                logr.info("Added " + value.getClass().getSimpleName() + " " + key + " to lruCache; capacity is " + hashMap.size() + " / " + count);

            }
            while (hashMap.size() > count) {
                hashMap.remove(lruEnforcer.poll());
                logr.info("Evicted " + value.getClass().getSimpleName() + " from lruCache; capacity is " + hashMap.size() + " / " + count);
            }
            return hashMap.get(key);
        } finally {
            lock.unlock();
        }
    }

    public V remove(Object key) {
        lock.lock();
        try {
            if (hashMap.containsKey(key)) {
                lruEnforcer.remove(key);
                return hashMap.remove(key);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            return null;
        }
        lock.lock();
        try {
            if (hashMap.containsKey(key)) {
                lruEnforcer.remove(key);
                hashMap.put(key, value);
                lruEnforcer.add(key);
                logr.info("Overwrote " + value.getClass().getSimpleName() + " " + key + " to lruCache");
            }
            return value;
        } finally {
            lock.unlock();
        }
    }

    public ArrayList<V> snapshot() {
        lock.lock();
        try {
            return new ArrayList<V>(hashMap.values());
        } finally {
            lock.unlock();
        }
    }
}
