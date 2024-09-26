package chunkproblem.Utility;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.BlockingDeque;
import java.util.HashMap;
import java.util.Map;

public class RetryWithDequeAndDLQ<E> {

    private final LinkedBlockingDeque<E> deque;
    private final BlockingDeque<E> deadLetterQueue;  // Dead letter queue
    private final Map<E, Integer> retryCountMap;      // Track retry counts
    private final int maxRetries = 3;                 // Max retries allowed

    public RetryWithDequeAndDLQ(int capacity) {
        deque = new LinkedBlockingDeque<>(capacity);
        deadLetterQueue = new LinkedBlockingDeque<>();
        retryCountMap = new HashMap<>();
    }

    // Take the element from the front, attempt to process it, and requeue if necessary
    public void processNext() throws InterruptedException {
        if (!deque.isEmpty()) {
//            System.out.println("calling next processNext");

            E element = deque.takeFirst();  // Take element from the front

            try {
                processElement(element);  // Attempt to process the element
                retryCountMap.remove(element);  // Remove from retry map on success
            } catch (Exception e) {
                System.err.println("Error processing element: " + element);

                int retryCount = retryCountMap.getOrDefault(element, 0) + 1;
                if (retryCount >= maxRetries) {
                    System.err.println("Max retries exceeded for: " + element + ", moving to dead letter queue...");
                    deadLetterQueue.putLast(element);  // Move to dead letter queue
                    retryCountMap.remove(element);     // Remove from retry map
                } else {
                    System.err.println("Retrying element: " + element + ", retry count: " + retryCount);
                    retryCountMap.put(element, retryCount);  // Update retry count
                    deque.putFirst(element);  // Requeue to the front for retry
                }
            }
        }
    }

    // Simulate element processing logic, can throw an exception if processing fails
    private void processElement(E element) throws Exception {
        // Simulate processing logic here
        if (Math.random() > 0.7) {  // Simulate a failure with 30% chance
            throw new Exception("Processing failed!");
        }
        System.out.println("Processed: " + element);
    }

    public void put(E element) throws InterruptedException {
        deque.putLast(element); // Normal put operation (to the end)
    }

    public int size() {
        return deque.size();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public int deadLetterQueueSize() {
        return deadLetterQueue.size();
    }

    public static void main(String[] args) throws InterruptedException {
        RetryWithDequeAndDLQ<String> retryWithDequeAndDLQ = new RetryWithDequeAndDLQ<>(10);

        // Adding elements to the deque
        retryWithDequeAndDLQ.put("Task1");
        retryWithDequeAndDLQ.put("Task2");
        retryWithDequeAndDLQ.put("Task3");

        // Processing loop with a delay
        while (!retryWithDequeAndDLQ.isEmpty()) {
            retryWithDequeAndDLQ.processNext();  // Keep trying to process elements
            Thread.sleep(500);  // Add a 500ms delay between processing each element
        }

        System.out.println("All tasks processed. Dead letter queue size: " + retryWithDequeAndDLQ.deadLetterQueueSize());
    }
}
