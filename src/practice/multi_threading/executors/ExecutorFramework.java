package practice.multi_threading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(9);

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println("result = " + result);
            });
        }
        executorService.shutdown();
        try {
//            while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
//                System.out.println("Waiting,,, ");
//            }
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total Time = " + (System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
