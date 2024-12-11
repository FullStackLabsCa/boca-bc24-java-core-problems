package io.reactivestax.cachelibraryv1;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class CacheMain {

    public static Thread startDaemonThread(CacheManagement<Integer, String> cacheManagement) {
        Thread daemonThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Running daemon thread");
                try {
                    Thread.sleep(1000);
                    cacheManagement.cleanExpiredValues();
                } catch (InterruptedException e) {
                    System.out.println("Daemon thread interrupted");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
       return daemonThread;
    }

    public static void main(String[] args) {

        CacheManagement<Integer,String> cacheManagement = new CacheManagement(new ConcurrentHashMap(), 10000 );
        cacheManagement.putKeyValue(1,"Shifa");
        cacheManagement.putKeyValue(2, "Manpreet");
        cacheManagement.putKeyValue(3, "Sukhvir");
        cacheManagement.putKeyValueTTL(4, "Kiran", 10);
        cacheManagement.putKeyValueTTL(5, "Richa", -1);

        startDaemonThread(cacheManagement);
        System.out.println("All keys:: " + cacheManagement.retrieveKeys());

        System.out.println("ID 2 is : " + cacheManagement.getValue(2));

        System.out.println("Key removal successful = " + cacheManagement.removeKey(1));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All keys:: " + cacheManagement.retrieveKeys());
        System.out.println("Current Size of map - " + cacheManagement.getSize());
        cacheManagement.doCleanup();
        System.out.println("Cleanup done, size of map is : " + cacheManagement.getSize());

    }
}
