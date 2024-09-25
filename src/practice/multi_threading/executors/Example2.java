package practice.multi_threading.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> integerFuture = executorService.submit(() -> 42);
        System.out.println("integerFuture.isDone() = " + integerFuture.isDone());
        System.out.println("integerFuture.get() = " + integerFuture.get());
        executorService.shutdown();
    }
}
