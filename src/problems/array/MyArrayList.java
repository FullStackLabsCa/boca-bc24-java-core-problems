package problems.array;

import java.util.Arrays;

@SuppressWarnings("java:S106")
public class MyArrayList<T> {
    int capacity = 10;
    T[] myArray;
    int index = 0;

    public MyArrayList() {
        myArray = (T[]) new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.myArray = (T[]) new Object[capacity];
    }

    public void add(T element) {
        myArray[index] = element;
        index++;
    }

    public T get(int index) {
        if (myArray[index] == null && index > this.index) {
            throw new IndexOutOfBoundsException();
        }
        return myArray[index];
    }

    public T remove(int index) {
        T valueAtIndex = myArray[index];
        if (myArray[index] == null) {
            throw new IndexOutOfBoundsException();
        }
        myArray[index] = null;

        for (int i = index; i < myArray.length - 1; i++) {
            myArray[i] = myArray[i + 1];
        }
        myArray[myArray.length - 1] = null;
        this.index--;
        return valueAtIndex;
    }

    public int size() {
        return index;
    }

    public void clear() {
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = null;
        }
        index = 0;
    }

    public boolean isEmpty() {
        return myArray[0] == null ? true : false;
    }

    @Override
    public String toString() {
        return Arrays.toString(myArray);
    }

    public void resize() {
    }

    public static void main(String[] args) {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>(5);
        integerMyArrayList.add(null);
        integerMyArrayList.add(20);
        integerMyArrayList.add(30);
        integerMyArrayList.add(40);
        integerMyArrayList.add(50);

        System.out.println("integerMyArrayList.get(1): " + integerMyArrayList.get(1));
        System.out.println("removing value: " + integerMyArrayList.remove(2));
        integerMyArrayList.size();
//        integerMyArrayList.clear();
        System.out.println("checking the array is Empty or not: " + integerMyArrayList.isEmpty());
        System.out.println("Array Value: " + integerMyArrayList.toString());
    }
}
