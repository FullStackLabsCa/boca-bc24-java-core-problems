package practice.threads;

public class Counter {
    public static void main(String[] args) {
        counterDemo demo = new counterDemo();
        Thread t1 = new Thread(demo, "Thread 1");
        Thread t2 = new Thread(demo, "Thread 2");
        t1.start();
        t2.start();
    }
}

class counterDemo implements Runnable {
    int i = 1;

    @Override
    public void run() {
        while (i <= 10) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " : counter = " + i);
                i++;
            }
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}