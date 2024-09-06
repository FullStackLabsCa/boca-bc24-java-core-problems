package problems.collection.myArrayList;

import java.util.Arrays;

public class MyArrayList<T> {

    private T[] array;
    private int size;

    public MyArrayList() {
        this.array = (T[]) new Object[10];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity<0){
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void add(T element) {
        if (size == array.length) {
            resize(); // Resize if the array is full
        }
        array[size] = element;
        size++;
    }

    private void resize() {
        // Create a new array with double the size
        T[] newArray = (T[]) new Object[array.length * 2];
        // Copy elements from old array to new array
        System.arraycopy(array, 0, newArray, 0, size);
        // Update the array reference to the new array
        array = newArray;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = array[index];
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // Clear the last element
        size--;
        return removedElement;
    }

    public int size() {
        return size;
    }

    public void clear() {
        this.size = 0;
        this.array = (T[]) new Object[10]; // Default capacity
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*@Override
    public String toString() {
        return "MyArrayList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("["); // Start with an opening bracket

        for (int i = 0; i < size; i++) {
            sb.append(array[i]); // Append the element at index i
            if (i < size - 1) {
                sb.append(", "); // Add a comma and space if not the last element
            }
        }

        sb.append("]"); // End with a closing bracket
        return sb.toString(); // Convert StringBuilder to String and return
    }


}
