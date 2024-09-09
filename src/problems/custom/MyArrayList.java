package problems.custom;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList<T> {
    int capacity =10;
    int index = 0;
    T[] myArray;
    T[] tempArray =(T[]) new Object[capacity * 2];


    public MyArrayList() {
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        myArray = (T[]) new Object[capacity];
    }

    public void add(T element) {
        myArray[index] = element;
        index++;
        if(capacity - index == 1) {
            myArray = Arrays.copyOf(myArray, capacity * 2);
            capacity *= 2;
        }
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if(myArray[index] == null && index>= this.index) {
            throw new IndexOutOfBoundsException();
        }
        return myArray[index];
    }

    public T remove(int index) {
        if(myArray[index] == null) {
            throw new IndexOutOfBoundsException();
        }
        T value = myArray[index];
        try {
            if (myArray[index] != null) {
                for (int i = index; i < myArray.length; i++) {
                    if (myArray[i] != null) {
                        myArray[i] = myArray[i + 1];
                    }
                }
            }
            this.index--;
            return value;
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int size() {
        System.out.println("size " + index);
        return index;
    }

    public void clear() {
        myArray =  Arrays.copyOf(tempArray, myArray.length);
        index = 0;

    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "capacity=" + capacity +
                ", index=" + index +
                ", myArray=" + Arrays.toString(Arrays.copyOf(myArray, size())) +
                '}';
    }

    public  boolean isEmpty() {
           return size() == 0;
    }

    private T[] resize() {
           return Arrays.copyOf(tempArray, capacity*2);
        }

    public static void main(String[] args) {
        MyArrayList<Object> objectMyArrayList = new MyArrayList<>(15);


        objectMyArrayList.add("Apple");
        objectMyArrayList.add("Banana");
        objectMyArrayList.add("Chery");
        objectMyArrayList.add(null);
        objectMyArrayList.add("Pineapple");
//        objectMyArrayList.add("Mango");
//        objectMyArrayList.add("Peach");
//        objectMyArrayList.add("Guava");
//        objectMyArrayList.add("papaya");
//        objectMyArrayList.add("sugarcane");
//        objectMyArrayList.add("litchi");
////        objectMyArrayList.resize();
//        objectMyArrayList.add("Pomogranate");
//        objectMyArrayList.add("Berry");

//        objectMyArrayList.size();
//        objectMyArrayList.remove(2);
//        objectMyArrayList.size();

//        objectMyArrayList.clear();

//        System.out.println("oop" + objectMyArrayList.toString());
        System.out.println("objectMyArrayList = " + objectMyArrayList);
        //testing ArrayList

        ArrayList<Integer> integerArrayList = new ArrayList<>(15);
        integerArrayList.add(0, 12);
        integerArrayList.add(1, null);
        integerArrayList.add(2, 12);
        integerArrayList.add(3, null);

        System.out.println("integerArrayList = " + integerArrayList);

    }
}
