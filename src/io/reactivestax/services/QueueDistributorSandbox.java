package io.reactivestax.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueDistributorSandbox {

    static ConcurrentHashMap<String, Integer> distributorMap = new ConcurrentHashMap<>();
    static int queueNumber = 1;
    public static LinkedBlockingQueue<String> queueOne = new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<String> queueTwo = new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<String> queueThree = new LinkedBlockingQueue<>();

    public QueueDistributorSandbox() {

    }

    public static int getQueueNumber(String accountNumber) {
        int queue = 1;

        if (distributorMap.containsKey(accountNumber)) {
            queue = distributorMap.get(accountNumber);
        } else {
            queue = queueNumber;
            distributorMap.put(accountNumber, queueNumber);
            queueNumber++;
            if (queueNumber > 3) {
                queueNumber = 1;
            }
        }
        return queue;
    }

    public static void assignQueueToAccountID(String tradeID, int queueNumber) throws InterruptedException {
        switch (queueNumber) {
            case 1:
                queueOne.put(tradeID);
                break;
            case 2:
                queueTwo.put(tradeID);
                break;
            case 3:
                queueThree.put(tradeID);
                break;
            default:
                break;
        }
    }

    public static void printMapAndQueue() {

        System.out.println("queueOne.size() = " + queueOne.size());
        System.out.println("queueTwo = " + queueTwo.size());
        System.out.println("queueThree = " + queueThree.size());
        System.out.println("Total queue size --- " + (queueOne.size() + queueTwo.size() + queueThree.size()));
    }
}


