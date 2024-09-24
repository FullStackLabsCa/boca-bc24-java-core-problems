package practice.multi_threading.thread;

class RequestHandler extends Thread {
    public void run() {
        System.out.println("Handling request...");
        try {
            Thread.sleep(2000); // Simulating request handling time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Request handled.");
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread requestThread = new RequestHandler();
        System.out.println("State: " + requestThread.getState()); // NEW
        requestThread.start();
        System.out.println("State: " + requestThread.getState()); // RUNNABLE
        Thread.sleep(500); // Give some time for thread to enter TIMED_WAITING
        System.out.println("State: " + requestThread.getState()); // TIMED_WAITING
        requestThread.join();
        System.out.println("State: " + requestThread.getState()); // TERMINATED
    }
}