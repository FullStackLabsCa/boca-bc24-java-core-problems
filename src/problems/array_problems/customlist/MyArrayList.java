package problems.customlist;

import java.util.Arrays;

public class MyArrayList <T> {
    public T[] myArray;
    public int arraySize;
    public int capacity;

    public MyArrayList(){
        this.capacity = 10;
        this.arraySize = 0;
        this.myArray = (T[]) new Object[capacity];
    }


    MyArrayList(int capacity){
        this.capacity = capacity;
        this.arraySize = 0;
        this.myArray = (T[]) new Object[capacity];
    }

    public void add(T element){
        if(arraySize == capacity){
            resize();
        }
        myArray[arraySize] = element;
        arraySize++;
        System.out.println("Element inserted: " + element);
    }

    public void resize() {
        capacity *=2;
        myArray = Arrays.copyOf(myArray, capacity);
        System.out.println("Array resized to: " + capacity);
    }

    public T get(int index){
        if (index < 0 || index >= arraySize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arraySize);
        }
        return myArray[index];
    }

    public T remove(int index){
        if (index < 0 || index >= arraySize){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arraySize);
        }

        T removedElement = myArray[index];

        for (int i = index; i < arraySize - 1; i++) {
            myArray[i] = myArray[i + 1];
        }
        myArray[arraySize - 1] = null;
        arraySize--;
        System.out.println("Element at index " + index + " removed.");

        return removedElement;
    }

    public int size() {
        return arraySize;
    }

    public void clear() {
        for (int i = 0; i < arraySize; i++) {
            myArray[i] = null;
        }
        arraySize = 0;
        System.out.println("List cleared.");
    }

    public Boolean isEmpty(){
        return arraySize==0;
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Pineapple");

        System.out.println("Element at index 1: " + list.get(1)); // Output: Banana

        list.remove(1);
        System.out.println("After removal: "); // Output: [Apple, Cherry]
        for(int i=0; i< list.size();i++){
            System.out.println(list.get(i));
        }
        // Check the size
        System.out.println("Size: " + list.size()); // Output: 2

        list.clear();
        System.out.println("After clear: " + list.isEmpty()); // Output: true
    }
}
