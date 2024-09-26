package problems.tradeProcessing.trades;

import java.util.concurrent.LinkedBlockingDeque;

public class TradeQueueDistributorTest {
    public static void main(String[] args) {
        TradeQueueDistributor distributor = new TradeQueueDistributor();

        // Simulate some trades
        String[] tradeIds = {"trade1", "trade2", "trade3", "trade4", "trade5"};
        String[] accountNumbers = {"accountA", "accountB", "accountA", "accountC", "accountB"};

        // Process trades and assign to queues
        for (int i = 0; i < tradeIds.length; i++) {
            String tradeId = tradeIds[i];
            String accountNumber = accountNumbers[i];

            // Assign queue to the account number
            distributor.assignQueue(accountNumber);
            int queueNumber = distributor.getQueue(accountNumber);
            distributor.writeTradeToQueue(tradeId, queueNumber);
        }

        // Verify the contents of each queue
        for (int i = 0; i < 3; i++) {
            LinkedBlockingDeque<String> queue = distributor.tradeQueues[i];
            System.out.println("Contents of queue " + i + ": " + queue);
        }
        distributor.printQueueContents();
    }
}
