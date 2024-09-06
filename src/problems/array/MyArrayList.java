package problems.array;

import java.util.Arrays;

public class MyArrayList <T>{
    public T[] list;

    @Override
    public String toString() {
        return "MyArrayList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }

    public int size= 0;

    public MyArrayList() {
        this.list= (T[]) new Object[0];
    }

    public MyArrayList(int capacity) {
        this.list= (T[]) new Object[capacity];
    }

    public void increaseCapacity(){
        T[] temp= (T[]) new Object[size+1];
        for(int i=0; i<list.length; i++){
            temp[i]= list[i];
        }
        list=temp;
    }

    public void add(T element) {
        if(list.length==size){
            increaseCapacity();
        }
        list[size]= element;
        size++;
    }

    public int size() {
        int count= 0;
        for(int i=0; i<list.length; i++){
           count++;
        }
        return count;
    }

    public T get(int i) {
        return list[i];
    }

    public T remove(int i) {
        T[] temp= (T[]) new Object[list.length-1];
        T removedElement= list[i];

        int tempIndex=0;
        for(int j=0; j<list.length; j++){
            if(j==i){
                continue;
            }
            temp[tempIndex++]=list[j];
        }
        list= temp;
        return removedElement;
    }

    public void clear() {
        T[] temp= (T[]) new Object[0];

        list= temp;
    }

    public boolean isEmpty() {
        return list.length==0;
    }
}
