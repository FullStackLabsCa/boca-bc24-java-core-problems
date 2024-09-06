package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.list;

public class MyArrayList <T>{
    private T[] ts;
    int size = 0;

    public MyArrayList() {
        this.ts = (T[]) new Object[0];
    }
    public void increaseCapacity(){
       ts = Arrays.copyOf(ts,ts.length+1);
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "ts=" + Arrays.toString(ts) +
                '}';
    }
    public void add(T element){
  try{
     if(ts.length==size){
         increaseCapacity();
     }
     ts[size] = element;
     size++;
  }catch (IndexOutOfBoundsException e){
      System.out.println("Index out of bound");
  }
    }

    public int size() {
        return size;
    }

    public T get(int index){
        return ts[index];
    }

    public T remove(int element) {
        List<T> newArray = new ArrayList<>(Arrays.asList(ts));
        T removedElement = newArray.get(element);
        newArray.remove(element);
        ts = Arrays.copyOf(ts,ts.length-1);
        size = ts.length;
        for(int i=0;i<newArray.size();i++){
            ts[i] = newArray.get(i);
        }
       return removedElement;
    }

    public void clear() {
        T[] newTs= (T[]) new Object[0];
        ts = Arrays.copyOf(newTs,0);
        size = 0;
    }

    public boolean isEmpty() {
        return ts.length == 0;

    }
}
