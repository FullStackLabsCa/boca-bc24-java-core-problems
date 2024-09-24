package practice.multi_threading.thread;

public class example {
        public static void main(String[] args) {
            // Traditional way using an anonymous class
            Runnable task1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task 1 is running.");
                }
            };

            Thread thread1 = new Thread(task1);
            thread1.start();

            // Another example
            Runnable task2 = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task 2 is running.");
                }
            };

            Thread thread2 = new Thread(task2);
            thread2.start();
        }
    }

