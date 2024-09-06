package problems.custom;

import java.util.Arrays;

public class MyArrayList<T> {
    int capacity =10;
    int index = 0;
    T[] myArray = (T[]) new Object[capacity];
    T[] tempArray =(T[]) new Object[capacity * 2];


    public MyArrayList() {
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
    }

    public void add(T element) {
        myArray[index] = element;
        index++;
        if(index> 9) {
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
                ", myArray=" + Arrays.toString(myArray) +
                '}';
    }

    public  boolean isEmpty() {
           return size() == 0;
    }

//    public String[] toString() {
//        String[] stringType = new String[myArray.length];
//        for (int i = 0; i < myArray.length ; i++) {
//            if (myArray[i] != null) {
//                stringType[i] = Arrays.toString(stringType);
//                System.out.println("Array element is: " + myArray[i]);
//            }
//        }
//        return stringType;
//    }

    private T[] resize() {
           return Arrays.copyOf(tempArray, capacity*2);
        }

    public static void main(String[] args) {
        MyArrayList<Object> objectMyArrayList = new MyArrayList<>();


        objectMyArrayList.add("Apple");
        objectMyArrayList.add("Banana");
        objectMyArrayList.add("Chery");
        objectMyArrayList.add("Pineapple");
        objectMyArrayList.add("Mango");
        objectMyArrayList.add("Peach");
        objectMyArrayList.add("Guava");
        objectMyArrayList.add("papaya");
        objectMyArrayList.add("sugarcane");
        objectMyArrayList.add("litchi");
//        objectMyArrayList.resize();
        objectMyArrayList.add("Pomogranate");
        objectMyArrayList.add("Berry");

        objectMyArrayList.size();
//        objectMyArrayList.remove(2);
        objectMyArrayList.size();

//        objectMyArrayList.clear();

        System.out.println("oop" + objectMyArrayList.toString());

    }
}
