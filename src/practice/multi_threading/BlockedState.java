package practice.multi_threading;

public class BlockedState {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DemoBlockedRunnable());
        Thread t2 = new Thread(new DemoBlockedRunnable());

        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println(t2.getState());  // Output: BLOCKED
    }
}

class DemoBlockedRunnable implements Runnable {
    public static synchronized void commonResource() {
        while (true) {
            // Infinite loop to simulate heavy processing
        }
    }

    @Override
    public void run() {
        commonResource();
    }
}