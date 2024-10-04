package multithreadingtrade.services;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;


public class QueueDistributor {
    private static final Random random = new Random();

    static ConcurrentHashMap<String, Integer> accountQueueMap = new ConcurrentHashMap<>();


    public static LinkedBlockingDeque<String> tradeQueueOne = new LinkedBlockingDeque<>();
    public static LinkedBlockingDeque<String> tradeQueueSecond = new LinkedBlockingDeque<>();
    public static LinkedBlockingDeque<String> tradeQueueThird = new LinkedBlockingDeque<>();




    public static int validateAccountNumberIntoTheQueue(String accountNumber){
        int randomNumber = getRandomNumber();
        if(accountQueueMap.contains(accountNumber)){
            randomNumber = accountQueueMap.get(accountNumber);
        } else {
            accountQueueMap.put(accountNumber,randomNumber);
        }
        return  randomNumber;
    }


    public static void writesToQueues(String[] payload, int queueNumber) {
        System.out.println("sending the tradeId"+ payload[0] + " to QueueNumber "+queueNumber);
        if (queueNumber == 1) {
            tradeQueueOne.add(payload[0]);
        }
        if (queueNumber == 2) {
            tradeQueueSecond.add(payload[0]);
        }
        if (queueNumber == 3) {
            tradeQueueThird.add(payload[0]);
        }
    }

    public static int getRandomNumber() {
        return random.nextInt(3) + 1;
    }

    public static void printAllQueueStats() {
        int totalSize = QueueDistributor.tradeQueueOne.size() + QueueDistributor.tradeQueueSecond.size() + QueueDistributor.tradeQueueThird.size();
        System.out.println("Size of Queue1: " + QueueDistributor.tradeQueueOne.size());
        System.out.println("Size of Queue2: " + QueueDistributor.tradeQueueSecond.size());
        System.out.println("Size of Queue3: " + QueueDistributor.tradeQueueThird.size());
        System.out.println("Total Size of All Queues: " + totalSize);
    }
}
