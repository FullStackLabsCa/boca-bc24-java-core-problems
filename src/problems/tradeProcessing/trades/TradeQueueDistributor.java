package problems.tradeProcessing.trades;

import problems.tradeProcessing.customeinterface.files.QueueDistributorConcurrentMapInterface;
import problems.tradeProcessing.customeinterface.files.TradeQueueWriterInterface;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeQueueDistributor implements QueueDistributorConcurrentMapInterface, TradeQueueWriterInterface {

    private ConcurrentMap<String, Integer> accountQueueMap;
    LinkedBlockingDeque<String>[] tradeQueues;
    private Random random = new Random();

    public TradeQueueDistributor() {
        this.accountQueueMap = new ConcurrentHashMap<>();

        // initialize three LinkedBlockingDeque queues
        this.tradeQueues = new LinkedBlockingDeque[3]; //tradeQueue -> 0,1,2
        for (int i = 0; i < 3; i++) {
            this.tradeQueues[i] = new LinkedBlockingDeque<>();
        }
    }

    @Override
    public void assignQueue(String accountNumber) {
        if(!accountQueueMap.containsKey(accountNumber)){
            int queueNumber = random.nextInt(3); // 0,1,2
            accountQueueMap.put(accountNumber, queueNumber);
        }
    }

    @Override
    public int getQueue(String accountNumber) {

        // return the assign queue number for given account number
        return accountQueueMap.getOrDefault(accountNumber, -1);
    }

    @Override
    public void writeTradeToQueue(String tradeId, int queueNumber) {
        if (queueNumber >= 0 && queueNumber < tradeQueues.length) {
            tradeQueues[queueNumber].offer(tradeId);
            System.out.println("Trade ID " + tradeId + " added to queue " + queueNumber);
        } else {
            System.out.println("Invalid queue number for trade ID " + tradeId);
        }
    }

    public void printQueueContents() {

        int[] accountCounts = new int[tradeQueues.length];

        // show all accounts in each queue
        for (int i = 0; i < tradeQueues.length; i++) {
            System.out.println("Contents of queue " + i + ": " + tradeQueues[i]);

            // Create a set to track unique accounts in the current queue
            Set<String> uniqueAccounts = new HashSet<>();
            for (String tradeId : tradeQueues[i]) {
                uniqueAccounts.add(tradeId);
            }
            accountCounts[i] = uniqueAccounts.size(); // Count of unique accounts in the current queue
        }

        // show the total account count for each queue
        for (int i = 0; i < accountCounts.length; i++) {
            System.out.println("Total accounts in queue " + i + ": " + accountCounts[i]);
        }
    }

}
