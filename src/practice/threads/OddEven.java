package practice.threads;

public class OddEven {
    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();


    }
}

class Demo implements Runnable{
    private static int num = 1;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (num > 20) break;
                if (num % 2 == 0) {
                    System.out.println(num + " is an even number" + " executed by Thread: " + Thread.currentThread().getName());
                } else {
                    System.out.println(num + " is an odd number" + " executed by Thread: " + Thread.currentThread().getName());
                }
                num++;  // Increment num
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
