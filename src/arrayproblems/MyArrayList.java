//NOT DONE YET
package arrayproblems;

import java.util.Arrays;

public class MyArrayList<T> {
    public T[] arrayElement;
    public int size;
    public static final int INITIAL_VALUE = 10;

    @Override
    public String toString() {
        return "MyArrayList{" +
                "arrayElement=" + Arrays.toString(arrayElement) +
                ", size=" + size +
                '}';
    }

    public MyArrayList() {
        arrayElement = (T[]) new Object[INITIAL_VALUE];
//        arrayElement = new T[INITIAL_VALUE];
        this.size = 0;
    }

    public void add(T element) {
        if (size == arrayElement.length) {
            checkCapacity();
        }
        arrayElement[size++] = element;
    }

    public void checkCapacity() {
        if (size == arrayElement.length) {
            resize();
        }
    }

    public void resize() {
        int newSizeArray = arrayElement.length * 2;
        arrayElement = Arrays.copyOf(arrayElement, newSizeArray);

    }

    public T get(int index) {
        if (index < 0 || index >= arrayElement.length) {
            throw new IndexOutOfBoundsException();
        }
        return arrayElement[index];
    }

    public T remove(int index) {

        if (index >= arrayElement.length) {
            throw new IndexOutOfBoundsException();
        }

        T removedElement = arrayElement[index];

        for (int i = index; i < arrayElement.length - 1; i++) {
            arrayElement[i] = arrayElement[i + 1];
        }
        return removedElement;
    }

    public int size() {

        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bound:  " + index);
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arrayElement[i] = null;
        }
        size = 0;
        arrayElement = (T[]) new Object[INITIAL_VALUE];
    }

    public boolean isEmpty() {

        return size == 0;
    }


    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("List: " + list);

        System.out.println("Element at index 1: " + list.get(1));

        list.remove(1);
        System.out.println("After Removel: " + list);

        // Check the size
        System.out.println("Size: " + list.size()); // Output: 2

        // Clear the list
        list.clear();
        System.out.println("After clear: " + list.isEmpty()); // Output: true
    }
}

