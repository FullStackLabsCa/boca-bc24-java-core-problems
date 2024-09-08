package problems.collections;

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
        if (index > capacity - 1) {
            resize();
        }
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
        return "MyArrayList{" +
                "capacity=" + capacity +
                ", myArray=" + Arrays.toString(Arrays.copyOf(myArray, size())) +
                ", index=" + index +
                '}';
    }

    public void resize() {
        int newLength = myArray.length * 2;
        myArray = Arrays.copyOf(myArray, newLength);
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        // Adding elements
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Print the list
        System.out.println("List: " + list); // Output: [Apple, Banana, Cherry]

        // Get an element
        System.out.println("Element at index 1: " + list.get(1)); // Output: Banana

        // Remove an element
        list.remove(1);
        System.out.println("After removal: " + list); // Output: [Apple, Cherry]

        // Check the size
        System.out.println("Size: " + list.size()); // Output: 2

        // Clear the list
        list.clear();
        System.out.println("After clear: " + list.isEmpty()); // Output: true
    }
}
