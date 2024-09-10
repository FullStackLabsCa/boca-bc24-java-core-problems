package problems.genericCollections;


import java.util.Arrays;

public class MyArrayList<T> {
    private T[] myArray;
    private int size;

//    LinkedList

    public MyArrayList(int capacity) {
        myArray = (T[]) new Object[capacity];
        size = 0;
    }

    public MyArrayList() {
        this(10);
    }

    public void add(T element) {
        if (size == myArray.length) {
            resize();
        }
        myArray[size++] = element;
    }

    public <T> T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) myArray[index];
    }

    public <T> T remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T indexValue = (T) myArray[index];
        for (int i = index; i < size - 1; i++) {
            myArray[i] = myArray[i + 1];
        }
        myArray[--size] = null;
        return indexValue;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            myArray[i] = null;
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(myArray, size));
    }

    public void resize() {
        int newSize = myArray.length * 2;
        T[] newArray = (T[]) new Object[newSize];
        myArray = Arrays.copyOf(newArray, newArray.length);
    }
}
