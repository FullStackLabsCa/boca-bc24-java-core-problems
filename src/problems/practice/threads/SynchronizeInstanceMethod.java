package problems.practice.threads;

public class SynchronizeInstanceMethod {
    public static void main(String[] args) throws InterruptedException {

        Account account1 = new Account();
        Account account2 = new Account();
        Thread t1 = new Thread(() -> account1.withdraw(500));
        Thread t2 = new Thread(() -> account1.withdraw(200));
        Thread t3 = new Thread(() -> account2.withdraw(400));

        t1.start();
        t2.start();
        t3.start();
    }
}

class Account{
    private double balance= 1000;

    public synchronized void withdraw(double amount){
        if(amount< balance){
            balance= balance - amount;
            System.out.println("Updated balance is: " + balance + Thread.currentThread().getName());
        }
        else {
            System.out.println("Not enough balance: " + balance + Thread.currentThread().getName());
        }

    }


}
