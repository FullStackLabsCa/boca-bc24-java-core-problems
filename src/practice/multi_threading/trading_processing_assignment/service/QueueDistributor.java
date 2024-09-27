package practice.multi_threading.trading_processing_assignment.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueDistributor {
    private final ConcurrentHashMap<String, String> queueDistributorMap = new ConcurrentHashMap<>();
    private final LinkedBlockingQueue<String> tradesQueue0 = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<String> tradesQueue1 = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<String> tradesQueue2 = new LinkedBlockingQueue<>();

    // Assign an account number to a queue (or reuse existing one)
    public String assignQueue(String accountNumber) {
        return queueDistributorMap.computeIfAbsent(accountNumber, this::assignRandomQueue);
    }

    // Randomly assign a queue (between 0, 1, 2)
    private String assignRandomQueue(String accountNumber) {
        int queueNum = (int) (Math.random() * 3);  // Randomly choose between 0, 1, 2
        return "tradesQueue-" + queueNum;
    }

    // Add tradeId to the appropriate queue based on queueId
    public void addToQueue(String queueId, String tradeId) {
        switch (queueId) {
            case "tradesQueue-0":
                tradesQueue0.add(tradeId);
                break;
            case "tradesQueue-1":
                tradesQueue1.add(tradeId);
                break;
            case "tradesQueue-2":
                tradesQueue2.add(tradeId);
                break;
        }
        System.out.println("Trade " + tradeId + " added to " + queueId);
    }

    // Retrieve a specific queue by its ID
    public LinkedBlockingQueue<String> getQueue(String queueId) {
        return switch (queueId) {
            case "tradesQueue-0" -> tradesQueue0;
            case "tradesQueue-1" -> tradesQueue1;
            case "tradesQueue-2" -> tradesQueue2;
            default -> throw new IllegalArgumentException("Invalid queue ID");
        };
    }
}
