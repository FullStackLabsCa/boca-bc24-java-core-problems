package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.ConfigLoader;
import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.TaskQueueDistributor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;


public class TradeDistributor implements TaskQueueDistributor{
    ConfigLoader configLoader= new ConfigLoader("/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/io/reacticestax/tradeprocessingmultithreadingassignment/Application.properties");
    public Integer queue_num = configLoader.getIntProperty("number.Of.trade.queues");
    private Integer currentQueueNum = 1;
    ConcurrentHashMap<String, Integer> accountToQueueMap = new ConcurrentHashMap<>();
    static LinkedBlockingDeque<String> tradeQueue1 = new LinkedBlockingDeque<>();
    static LinkedBlockingDeque<String> tradeQueue2 = new LinkedBlockingDeque<>();
    static LinkedBlockingDeque<String> tradeQueue3 = new LinkedBlockingDeque<>();

    public static LinkedBlockingDeque<String> getTradeQueue1() {
        return tradeQueue1;
    }

    public static LinkedBlockingDeque<String> getTradeQueue2() {
        return tradeQueue2;
    }

    public static LinkedBlockingDeque<String> getTradeQueue3() {
        return tradeQueue3;
    }

    @Override
    public Integer consultAccountToQueueMap(String account_number) {
        return 1;
//        Integer queue;
//        if (accountToQueueMap.containsKey(account_number)) {
//           queue=  accountToQueueMap.get(account_number);
//        } else {
//                accountToQueueMap.put(account_number, currentQueueNum);
//                queue = currentQueueNum;
//            currentQueueNum++;
//                if (currentQueueNum > 3) {
//                    currentQueueNum = 1;
//                }
//            System.out.println("map size: " + accountToQueueMap.size());
//        }
//        return queue;
    }

    @Override
    public void insertToTradeQueue(String account_number, String trade_id) throws InterruptedException {
        int queueNumber = consultAccountToQueueMap(account_number);
        switch (queueNumber) {
            case 1:
                tradeQueue1.put(trade_id);
                System.out.println("sent to queue1 " + trade_id);
                break;
            case 2:
                tradeQueue1.put(trade_id);
                System.out.println("sent to queue1" + trade_id);
                break;
            case 3:
                tradeQueue1.put(trade_id);
                System.out.println("sent to queue1" + trade_id);
                break;
            default:
                break;
        }
    }

}
