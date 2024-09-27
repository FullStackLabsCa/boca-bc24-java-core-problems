package practice.multi_threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// in this example we are learning how avoid the unnecessary locking, atleast other thread can read the locked resources.

public class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();


    public void increment() {
        writeLock.lock();
        try {
            count++;
            Thread.sleep(1000);
            //CPU will think that this thread is now in sleep so lets give the chance to another threads to read this.
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }
    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter readWriteCounter = new ReadWriteCounter();
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " read: " + readWriteCounter.getCount());
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " incremented. ");
                    readWriteCounter.increment();
//                    System.out.println(Thread.currentThread().getName() + " incremented. ");
                }

            }
        };

        Thread writeThread = new Thread(writeTask);
        Thread readThread1 = new Thread(readTask);
        Thread readThread2 = new Thread(readTask);
        Thread writeThread2 = new Thread(writeTask);

        writeThread.start();
        readThread1.start();
        readThread2.start();
        writeThread2.start();

        writeThread.join();
        readThread1.join();
        readThread2.join();
        writeThread2.join();

        System.out.println("Final count: " + readWriteCounter.getCount());
    }
}
