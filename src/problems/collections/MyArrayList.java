package problems.collections;

import java.util.Arrays;

public class MyArrayList<T> {
    private T[] arrayList;
    int sizeOfArray;
    private static final int defaultCapacity = 10;


    //ArrayList with default capacity
    public MyArrayList() {
        arrayList = (T[]) new Object[defaultCapacity];
        sizeOfArray = 0;
    }

    //constructor - initializes list with user - defined capacity
    public MyArrayList(int capacity) {
        arrayList = (T[]) new Object[capacity];
        sizeOfArray = 0;
    }

    //adding element at the end of the list
    //call the method to double the size of the array - resize()
    public void add(T element) {
        if (sizeOfArray== arrayList.length) {
            resize();
        }
        arrayList[sizeOfArray++] = element;
    }

    //get method to return the element at specific index
    public T get(int index) {
        if (index < 0 || index >= sizeOfArray) {
            throw new IndexOutOfBoundsException();
        } else {
            return arrayList[index];
        }
    }

    //removing the elements at a specified index and shift the elements in array after removal
    public T remove(int index) {
        T newArray = arrayList[index];
        if (index < 0 || index >= sizeOfArray) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < arrayList.length - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[sizeOfArray -1] = null;
        sizeOfArray--;
        return newArray;
    }

    //size of current number of elements
    public int size() {
        return sizeOfArray;
    }

    //clearing the array and resetting it to defaultCapacity
    public void clear() {
        arrayList = (T[]) new Object[defaultCapacity];
        sizeOfArray = 0;
    }

    //checks if arrayList has any values or not
    public boolean isEmpty() {
        if (sizeOfArray == 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "MyArrayList { " +
                "arrayList = " + Arrays.toString(arrayList) +
                '}';
    }

    //resizes the arraylist to double the size when it's full
    private void resize() {
        if (sizeOfArray == arrayList.length) {
            int newSizeOfArray = arrayList.length * 2;
            arrayList = Arrays.copyOf(arrayList, newSizeOfArray);

        }
    }


    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();


        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");


        System.out.println("List: " + list);


        System.out.println("Element at index 1: " + list.get(1));


        list.remove(1);
        System.out.println("After removal: " + list);


        System.out.println("Size: " + list.size());


        list.clear();
        System.out.println("After clear: " + list.isEmpty());
    }
}

