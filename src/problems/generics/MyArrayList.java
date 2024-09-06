package problems.generics;

public class MyArrayList<T> {

    T[] arrayList;


    public MyArrayList() {
        arrayList = (T[]) new Object[10];
    }


    public MyArrayList(int capacity) {
        arrayList = (T[]) new Object[capacity];
    }

    // to add an element at the end of the array; if array of full then
    // create a new temp array with double current size and add it.
    public void add(T t) {
        int currentArrayItemsCount = this.size();
        if (currentArrayItemsCount < arrayList.length) {
            arrayList[currentArrayItemsCount] = t;
        } else {
            T[] temp = (T[]) new Object[this.size() * 2];
            System.arraycopy(arrayList, 0, temp, 0, currentArrayItemsCount);
            this.arrayList = temp;
            arrayList[currentArrayItemsCount] = t;
        }
    }

    //to get an element at specified
    public T get(int index) {
        int currentArrayItemsCount = this.size();
        if ((index < 0) || (index > currentArrayItemsCount - 1)) {
            throw new IndexOutOfBoundsException();
        } else {
            return arrayList[index];
        }
    }

    public T remove(int index) {
        int currentArrayItemsCount = this.size();
        if ((index < 0) || (index > currentArrayItemsCount - 1)) {
            throw new IndexOutOfBoundsException();
        } else {
            System.out.println("printing");
            T element = arrayList[index];
            System.arraycopy(arrayList, index + 1, arrayList, index, ((currentArrayItemsCount - 1) - index));  //since index is 0 bases
            arrayList[currentArrayItemsCount - 1] = null;
            System.out.println("after remove");
            return element;
        }
    }

    public void clear() {
        arrayList = (T[]) new Object[10];
    }

    public boolean isEmpty() {
        int currentArrayItemsCount = this.size();
        return (currentArrayItemsCount == 0);
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        int currentArrayItemsCount = this.size();
//        if (currentArrayItemsCount == 1) {
//            return "[" + arrayList[0] + "]";
//        }

        String result = arrayList[0].toString();
        for (int i = 1; i < currentArrayItemsCount; i++) {
            result = String.join(",", result, arrayList[i].toString());
        }

        return "[" + result + "]";
    }

    public int size() {
        int length = arrayList.length;
        int count = 0;
        for (T item : arrayList) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }
}
