package practice.threads;

import java.util.Arrays;

public class SortingOfArray {
    public static void main(String[] args) {
        SortingOfArrayDemo demo = new SortingOfArrayDemo();
        Thread t1 = new Thread(demo,"Thread 1");
        Thread t2 = new Thread(demo,"Thread 2");
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sorted Array : ");
        for(int num : demo.arr){
            System.out.print(num+" ");
        }
        System.out.println();
    }
}

class SortingOfArrayDemo implements Runnable{
    int[] arr = {4,7,2,9,1,6};

    @Override
    public void run(){
            synchronized (this) {
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = 0; j < arr.length - 1 - i; j++) {
                        if (arr[j] > arr[j + 1]) {
                            int temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " is sorting : " + Arrays.toString(arr));
                }
            }
    }
}
