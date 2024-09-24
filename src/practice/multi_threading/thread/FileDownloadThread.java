package practice.multi_threading.thread;

public class FileDownloadThread extends Thread {
    private String fileName;

    public FileDownloadThread(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        System.out.println("Downloading " + fileName + "...");
        try {
            Thread.sleep(2000); // Simulate file download time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fileName + " download complete.");
    }
}

