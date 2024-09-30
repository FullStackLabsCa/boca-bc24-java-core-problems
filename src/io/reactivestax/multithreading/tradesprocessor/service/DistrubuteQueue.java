package io.reactivestax.multithreading.tradesprocessor.service;

import io.reactivestax.multithreading.tradesprocessor.interfaces.QueueDistributor;

import java.util.concurrent.LinkedBlockingQueue;

public class DistrubuteQueue {
    static final LinkedBlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
    static final LinkedBlockingQueue<String> queue2 = new LinkedBlockingQueue<>();
    static final LinkedBlockingQueue<String> queue3 = new LinkedBlockingQueue<>();

    public static LinkedBlockingQueue<String> getQueue1() {
        System.out.println("q1"+queue1.size());
        return queue1;
    }

    public static LinkedBlockingQueue<String> getQueue2() {
        System.out.println("q2"+queue2.size());
        return queue2;
    }

    public static LinkedBlockingQueue<String> getQueue3() {
        System.out.println("q3"+queue3.size());
        return queue3;
    }

    public void distribute(String tradeId, int queueNumber) {
        if(queueNumber == 1){
            queue1.add(tradeId);
        } else if (queueNumber ==2) {
            queue2.add(tradeId);
        }else{
            queue3.add(tradeId);
        }
//        System.out.println("Submit Q3:: "+queue3.size());
    }

}
