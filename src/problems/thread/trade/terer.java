package problems.thread.trade;

import javax.xml.transform.Source;

public class terer implements Runnable{
    private String filePath;

    public terer(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
