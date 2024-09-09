package collections;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList <T>{


    private  T[] myArray;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    int index=0;


    public MyArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.myArray =  (T[])new Object[this.capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.myArray =  (T[])new Object[this.capacity];
    }


    public void add(T element) {

        // Check if array is full, if yes then increase capacity
        if (index == capacity) {
            capacity *= 2;
            myArray = Arrays.copyOf(myArray, capacity);
        }
        // Add element at the next available index
        myArray[index] = element;
        index++;
    }



    public T get(int index){
        if (capacity == 0 ){
            throw new NoSuchElementException();
        }
        return myArray[index];
    }


    public T remove(int index){
        int lastIndex=myArray.length - 1;
        if(index> myArray.length){
           throw new ArrayIndexOutOfBoundsException();
       }else if(index < myArray.length) {
           for(int i = index ; i<lastIndex; i++) {
                   myArray[i] = myArray[i + 1];
               capacity--;
           }
       }
       return myArray[index];
    }


    public int size(){
        return capacity;
    }

    public Object clear(){
        //Clears the list by removing all elements
        // and resetting the internal array to the default capacity.

       return null;
    }


    public boolean isEmpty(){
        return false;
    }


    @Override
    public String toString() {
        return "MyArrayList{" +
                "myArray=" + Arrays.toString(myArray) +
                ", index=" + index +
                '}';
    }

    public void resize(){


    }









}
