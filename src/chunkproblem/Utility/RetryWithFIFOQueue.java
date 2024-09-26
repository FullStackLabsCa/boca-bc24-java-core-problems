package chunkproblem.Utility;

import java.util.concurrent.ArrayBlockingQueue;

public class RetryWithFIFOQueue<E> {

    private final ArrayBlockingQueue<E> queue;

    public RetryWithFIFOQueue(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    // Take element and process
    public void processNext() throws InterruptedException {
        E element = queue.take();  // Take element from front
        try {
            processElement(element);  // Try to process the element
        } catch (Exception e) {
            System.err.println("Error processing element: " + element + ", requeueing...");
            queue.put(element);  // Put it back at the end if processing fails
        }
    }

    // Simulate element processing logic, can throw an exception if processing fails
    private void processElement(E element) throws Exception {
        // Processing logic here
        if (Math.random() > 0.7) {  // Simulate a failure with 30% chance
            throw new Exception("Processing failed!");
        }
        System.out.println("Processed: " + element);
    }

    public void put(E element) throws InterruptedException {
        queue.put(element);  // Normal put operation
    }

    public static void main(String[] args) throws InterruptedException {
        RetryWithFIFOQueue<String> retryWithFIFOQueue = new RetryWithFIFOQueue<>(10);
        retryWithFIFOQueue.put("Task1");
        retryWithFIFOQueue.put("Task2");
        retryWithFIFOQueue.put("Task3");

        while (true) {
            retryWithFIFOQueue.processNext();  // Keep trying to process elements
            Thread.sleep(500);
        }
    }
}
