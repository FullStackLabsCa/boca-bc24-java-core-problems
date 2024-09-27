package practice.multi_threading.trading_processing_assignment.contracts;

public interface QueueDistributor {
    // Assign a queue to an account number
    String assignQueue(String accountNumber);

    // Add a trade ID to a specific queue
    void addToQueue(String queueId, String tradeId);
}
