package problems.tradingwiththreads.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueDistributor {

    static ConcurrentHashMap<String, Integer> distributorMap = new ConcurrentHashMap<>();
    static int queueNumber = 0;

    static LinkedBlockingDeque<String> queueOne = new LinkedBlockingDeque<>();
    static LinkedBlockingDeque<String> queueTwo = new LinkedBlockingDeque<>();
    static LinkedBlockingDeque<String> queueThree = new LinkedBlockingDeque<>();

    private QueueDistributor() {

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
//        System.out.println("tradeConcurrentHashMap = " + distributorMap);
//        System.out.println("tradesQueue1 = " + queueOne);
//        System.out.println("tradesQueue2 = " + queueTwo);
//        System.out.println("tradesQueue3 = " + queueThree);
        System.out.println("queueOne.size() = " + queueOne.size());
        System.out.println("queueTwo = " + queueTwo.size());
        System.out.println("queueThree = " + queueThree.size());
    }
}


