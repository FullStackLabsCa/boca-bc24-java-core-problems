package practice.multi_threading.avoid_race_condition.reentrant_lock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    class Counter {
        private int count = 0;
        private Lock lock = new ReentrantLock();

        public void increment() {
            lock.lock(); // Acquire the lock
            try {
                count++;
            } finally {
                lock.unlock(); // Always release the lock
            }
        }

        public int getCount() {
            return count;
        }

    }


