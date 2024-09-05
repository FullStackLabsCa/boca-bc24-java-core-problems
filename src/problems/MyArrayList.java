package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  MyArrayList <T> {
    T[] ts;
    int size = 0;
    public static void main(String[] args) {

    }

    public MyArrayList(){
        this.ts = (T[]) new Object[10];
    }
    public MyArrayList(int capacity){
        this.ts = (T[]) new Object[capacity];
    }

    public void increseSize(){
        ts = Arrays.copyOf(ts,ts.length*2);
    }

    public void add(T element){
        try{
            if(ts.length == size){
                increseSize();
            }
            ts[size] = element;
            size++;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
    }
    
    public int size(){
        return size;
    }
    
    public <T> Object get(int position){
        return ts[position];
    }

//    public void remove(int position){
//        ts.remove(position);
//    }


}
