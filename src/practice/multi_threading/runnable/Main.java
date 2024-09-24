package practice.multi_threading.runnable;

class MyTask implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Task: " + i);
            try {
                Thread.sleep(1000);  // Simulates work
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask());
        Thread t2 = new Thread(new MyTask());

        t1.start();  // Start the first thread
        t2.start();  // Start the second thread
    }
}