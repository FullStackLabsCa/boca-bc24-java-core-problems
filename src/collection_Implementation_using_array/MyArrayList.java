package collection_Implementation_using_array;

import java.util.Arrays;

public class MyArrayList<T>  {
    public Object[] myArray;
    public int size;

    public MyArrayList() {
        myArray = new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity >= 0) {
            myArray = new Object[capacity];
        } else {
            throw new RuntimeException();
        }
    }


    public boolean isArrayFull(Object[] myArray) {
        if (myArray[myArray.length - 1] == null) {
            return false;
        } else return true;
    }

    public Object[] resizingArray(Object[] myArray) {
        this.myArray = new Object[(2 * myArray.length)];
        System.arraycopy(myArray, 0, this.myArray, 0, myArray.length);
        return this.myArray;
    }

    public void add(T t) {
        for (int i = 0; i < myArray.length; i++) {
            if (!isArrayFull(myArray)) {
                if (myArray[i] == null) {
                    myArray[i] = t;
                    size++;
                    break;
                }
            } else {
                Object[] objects = resizingArray(myArray);
                objects[size()]=t;
                size++;
                break;
            }
        }
    }

    public Object get(int index) {
        if (index > (size())) {
            throw new IndexOutOfBoundsException();
        } else {
            return myArray[index];
        }
    }

    public String remove(int index) {
        String removeElement;
        if (index > (size())) {
            throw new IndexOutOfBoundsException();
        } else {
            removeElement=String.valueOf(myArray[index]);
            myArray[index] = null;
            for (int i = index; i < (size()); i++) {
                myArray[index] = myArray[index + 1];
                myArray[index+1]=null;
                size--;
            }
        }
        return removeElement;
    }

    public int size() {
        return size;
    }

    public void clear() {
        if (myArray.length > 10) {
            myArray = new Object[10];
        } else {
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i] != null) {
                    myArray[i] = null;
                    size--;
                }
            }
        }
    }

    public boolean isEmpty() {
        int count = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] == null) {
                count++;
            } else count--;
        }
        if (count == myArray.length) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "collection_Implementation_using_array.MyArrayList{" +
                "myArray=" + Arrays.toString(myArray) +
                '}';
    }
}
