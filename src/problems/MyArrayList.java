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
        if(position <= size){
                return ts[position];
            }
            else{
                throw new IndexOutOfBoundsException();
            }
    }

    public String remove(int position){

        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }
        String removedElement = ts[position].toString();
            if (ts[position].equals(null)) {
                throw new IndexOutOfBoundsException();
            }
            for (int i = position; i < size - 1; i++) {
                ts[i] = ts[i + 1];
            }
            ts[size - 1] = null;
            size--;
        return removedElement;
    }

    public void clear(){
        Arrays.fill(ts,0,size,null);
        size =0;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }
}
