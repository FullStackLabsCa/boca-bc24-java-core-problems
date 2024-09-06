package problems.collections.my_array_list;
import java.util.ArrayList;
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

    @Override
    public String toString() {
        return  Arrays.toString(Arrays.copyOf(myArray, size));
    }


    public static void main(String[] args) {
        //Array testing
        int[] array = new int[10];
        array[0]=45;
        array[2] = 12;
        array[4] = 23;
        System.out.println("array[1] = " + array[1]);
        for (int i = 0; i < array.length; i++) {
            System.out.print(" ");
            System.out.print(array[i]);
        }

        //ArrayList testing
        System.out.println(" ");
        System.out.println("***************************************************");
        ArrayList<Integer> arrayList = new ArrayList<>(15);
        arrayList.add(0,11);
        arrayList.add(1, null);
//        arrayList.add(1, 12);
        arrayList.add(2,14);
        arrayList.add(3, null);

//        System.out.println("arrayList.size() = " + arrayList.size());
//        for (int element : arrayList) {
//            System.out.print(" "+ element);
//        }
        System.out.println("arrayList = " + arrayList);

        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(12);
        myArrayList.add(null);
        myArrayList.add(14);
        myArrayList.add(null);
        System.out.println("myArrayList = " + myArrayList);
    }
}
