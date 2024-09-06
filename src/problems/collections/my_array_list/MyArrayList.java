package problems.collections.my_array_list;


import java.util.Arrays;

public class MyArrayList<T> {
    private static final int defaultCapacity = 10;  //default size of the ArrayList
    private Object[] myArray;
    private int size;   //the size of the ArrayList


    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.myArray = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList() {
        this.myArray = new Object[defaultCapacity];
    }

    // logic to find the new length
    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
        if (prefLength > 0) {
            return prefLength;
        } else {
            throw new OutOfMemoryError("Required array length " + oldLength + " + " + minGrowth + " is too large");
        }
    }

    //how to resize the Array
    private Object[] resizeArray(int minCapacity) {
        int oldCapacity = myArray.length;
        if (oldCapacity > 0) {
            int newCapacity = newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            return myArray = Arrays.copyOf(myArray, newCapacity);
        } else {
            return myArray = new Object[Math.max(defaultCapacity, minCapacity)];
        }
    }

    private Object[] resizeArray() {
        return resizeArray(size + 1);
    }

    public int size() {
        return size;
    }


    public void add(T element) {
        // check if there is space before adding the element into myArray
        if (size >= myArray.length) {
            resizeArray(); //resize if there is no space
        }
        //Add element to Array
        myArray[size] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else
            return (T) myArray[index];
    }

    public T remove(int index) {
        T removedElement = (T) myArray[index];
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else {
            for (int i = index; i < size - 1; i++) {
                myArray[i] = myArray[i + 1];
            }
            myArray[size - 1] = null; //remove the last element from the myArray
            size--;
        }
        return removedElement;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else
            return false;
    }

    public void clear() {
       size = 0;
    }
}
