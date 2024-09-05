package problems.collections.myarraylist;

import java.util.Arrays;

public class MyArrayList<T> {
    private T[] array;
    private static int currentIndex;

    public MyArrayList() {
        array = (T[]) new Object[1];
        currentIndex = -1;
    }

    public MyArrayList(int size) {
        array = (T[]) new Object[size];
        currentIndex = -1;
    }

    public void add(T element) {
        currentIndex++;
        if (currentIndex == array.length) resize();
        array[currentIndex] = element;
    }

    public T get(int index) {
        if (index > -1 && index <= currentIndex) return array[index];
        else throw new IndexOutOfBoundsException();
    }

    public T remove(int index) {
        if (index > -1 && index <= currentIndex) {
            T temp = array[index];
            array[index] = null;
            if (index != currentIndex) {
                for (int i = index; i < currentIndex; i++) {
                    array[i] = array[i + 1];
                }
            }
            currentIndex--;
            return temp;
        } else throw new IndexOutOfBoundsException();
    }

    public int size() {
        return currentIndex + 1;
    }

    public void clear() {
        currentIndex = -1;
    }

    public boolean isEmpty() {
        return currentIndex == -1;
    }

    private void resize() {
        array = Arrays.copyOf(array, array.length + 1);
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
