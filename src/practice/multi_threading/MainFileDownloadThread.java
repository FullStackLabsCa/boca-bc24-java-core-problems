package practice.multi_threading;

import practice.multi_threading.thread.FileDownloadThread;

public class MainFileDownloadThread {
    public static void main(String[] args) {
        FileDownloadThread download1 = new FileDownloadThread("file1.txt");
        FileDownloadThread download2 = new FileDownloadThread("file2.txt");

        download1.start();
        download2.start();
    }
}
class BankTransaction implements Runnable {
    private String transactionType;

    public BankTransaction(String transactionType) {
        this.transactionType = transactionType;
    }

    public void run() {
        System.out.println("Processing " + transactionType + " transaction...");
        try {
            Thread.sleep(1500); // Simulate transaction processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(transactionType + " transaction complete.");
    }
}


class MainRunnable {
    public static void main(String[] args) {
        Thread transaction1 = new Thread(new BankTransaction("Deposit"));
        Thread transaction2 = new Thread(new BankTransaction("Withdrawal"));

        transaction1.start();
        transaction2.start();
    }
}