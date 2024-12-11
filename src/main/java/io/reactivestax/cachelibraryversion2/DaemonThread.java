package io.reactivestax.cachelibraryversion2;

public class DaemonThread<K,V> extends Thread{
    private CacheLibrary<K,V> cacheLibrary;

    public DaemonThread(CacheLibrary<K,V> cacheLibrary){
        this.cacheLibrary = cacheLibrary;
    }

    @Override
    public void run(){
        while(true){
            try{
                cacheLibrary.monitorEviction();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
