package collection;
import java.util.Arrays;

public class MyArrayList<T> {
    private T[] arrayList;//Object[] internally to store the elements.
    int sizeOfArray;
    private static final int Default_Capacity = 10;// default_capacity

    @Override
    public String toString() {  // method to return a string representation
        return "MyArrayList{" +
                "arrayList=" + arrayList +
                '}';
    }
    public MyArrayList(int initialCapacity) {//constructor with Default Capacity value
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        arrayList = (T[]) new Object[initialCapacity];
        sizeOfArray = 0;
    }

    public MyArrayList() { //initializes the list with a user-defined capacity.
        arrayList = (T[]) new Object[Default_Capacity];
        sizeOfArray = 0;
    }

    public void add(T element) {// adding it to the list
        if (sizeOfArray == arrayList.length) {
            resize();
        }
        arrayList[sizeOfArray++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= sizeOfArray) {
            throw new IndexOutOfBoundsException("Value cannot be negative or greater than size.");
        }
        return arrayList[index];
    }

    public T remove(int index) {// removing from a specific index and resizing an array
        if (index > sizeOfArray || index < 0) {
            throw new IndexOutOfBoundsException("Enter a Valid value");
        }
        T removedElement = arrayList[index];
        for (int i = index; i < sizeOfArray - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        sizeOfArray--;
        return removedElement;
    }

    public int size() {//Returns the current number of elements in the list.
        return this.sizeOfArray;
    }

    public void clear() {//Clears the list by removing all elements and resetting the internal array to the default capacity.
        arrayList = (T[]) new Object[Default_Capacity];
        sizeOfArray = 0;

    }

    public boolean isEmpty() { //Returns true if the list contains no elements
        if (sizeOfArray == 0) {
            return true;
        }
        return false;
    }

    public void resize() {
        int newsize = arrayList.length * 2;
        arrayList = Arrays.copyOf(arrayList, newsize);

    }
}
