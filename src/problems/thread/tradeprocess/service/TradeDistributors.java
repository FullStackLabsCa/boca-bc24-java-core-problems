package thread.tradeprocess.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeDistributors {
    protected static ConcurrentMap<String, String> tradeConcurrentHashMap = new ConcurrentHashMap<>();
    protected static LinkedBlockingQueue<String> tradesQueue1 = new LinkedBlockingQueue<>();
    protected static LinkedBlockingQueue<String> tradesQueue2 = new LinkedBlockingQueue<>();
    protected static LinkedBlockingQueue<String> tradesQueue3 = new LinkedBlockingQueue<>();
    public static int queueNo = 1;

    public TradeDistributors(ConcurrentHashMap<String, String> tradeConcurrentHashMap,
                             LinkedBlockingQueue<String> tradesQueue1,
                             LinkedBlockingQueue<String> tradesQueue2,
                             LinkedBlockingQueue<String> tradesQueue3) {
        this.tradeConcurrentHashMap = tradeConcurrentHashMap;
        this.tradesQueue1 = tradesQueue1;
        this.tradesQueue2 = tradesQueue2;
        this.tradesQueue3 = tradesQueue3;
    }

    public static String addToConcurrentHashMap(String accountNo) {

        String queue;
        if (!tradeConcurrentHashMap.containsKey(accountNo)) {
            synchronized (TradeDistributors.class) {
                if (queueNo == 4)
                    queueNo = 1;

                switch (queueNo) {
                    case 1: tradeConcurrentHashMap.put(accountNo, "tradesQueue1");
                        break;
                    case 2: tradeConcurrentHashMap.put(accountNo, "tradesQueue2");
                        break;
                    case 3: tradeConcurrentHashMap.put(accountNo, "tradesQueue3");
                        break;
                    default: break;
                }
                queueNo++;
            }

        }
        queue = tradeConcurrentHashMap.get(accountNo);
        return queue;
    }

    public static void addToLinkedBlockingQueue(String queue, String tradeId) throws InterruptedException {
        switch (queue) {
            case "tradesQueue1": tradesQueue1.put(tradeId);
                break;
            case "tradesQueue2": tradesQueue2.put(tradeId);
                break;
            case "tradesQueue3":tradesQueue3.put(tradeId);
                break;
            default: break;
        }
    }

    public static void printMapAndQueue() {
        System.out.println("tradeConcurrentHashMap = " + tradeConcurrentHashMap);
        System.out.println("tradesQueue1 = " + tradesQueue1);
        System.out.println("tradesQueue2 = " + tradesQueue2);
        System.out.println("tradesQueue3 = " + tradesQueue3);
    }
}
