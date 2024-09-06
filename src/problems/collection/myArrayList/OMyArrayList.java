package problems.collection.myArrayList;

import java.util.Arrays;

public class OMyArrayList<T> {

    private T[] array;
    private static int currentIndex = -1;

    public OMyArrayList() {
        array = (T[]) new Object[1];
    }

    public OMyArrayList(int size) {
        array = (T[]) new Object[size];
    }

    public void add(T t) {

        if (currentIndex + 1 >= array.length) {
//            array = Arrays.copyOf(array, array.length + 1);
            resize();
        }
        array[++currentIndex] = t;
    }

    public T get(int i) {

        if (i >= 0 && i <= currentIndex) {
            return array[i];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public  T remove(int i){

        if (i>=0 && i <= currentIndex) {
            T temp = array[i];
            // Shift elements to the left
            for (int j = i; j < currentIndex; j++) {
                array[j] = array[j + 1];
            }
            array[currentIndex] = null; // remove last element
            currentIndex--;
            return temp;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size(){
        return currentIndex + 1;
    }

    public boolean isEmpty(){
        return currentIndex == -1;
    }

    public void clear(){
        array = (T[]) new Object[array.length];
        currentIndex = -1;
    }

    private void resize(){
        array = Arrays.copyOf(array, array.length * 2);
    }


    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
