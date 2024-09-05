package arraylist;


public class MyArrayList<T> {
    T arrayElements [];
    static int arrayIndex = 0;

    public MyArrayList() {
        arrayIndex = 0;
        arrayElements = (T[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        arrayIndex = 0;
        arrayElements = (T[]) new Object[capacity];
    }

    public int getArraySize() {
        int count = arrayIndex;
        return count;
    }

    public void add(T element) {
        if (arrayIndex >= arrayElements.length) {
            resize();
        }
        arrayElements[arrayIndex++] = element;
    }

    public T get(int index) {
        if (index >= arrayIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arrayElements[index];
    }

    public T remove(int index) {
        if (index >= arrayIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removedElement = arrayElements[index];
        if (index != arrayIndex - 1) {
            for (int i = index; i < arrayIndex; i++) {
                arrayElements[i] = arrayElements[i + 1];
            }
        }

        arrayIndex--;
        return removedElement;
    }

    public int size() {
        return getArraySize();
    }

    public void clear() {
        arrayElements = (T[]) new Object[10];
        arrayIndex = 0;
    }

    public boolean isEmpty() {
        int count = getArraySize();

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arrayIndex; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(arrayElements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void resize() {
        T newArrayElements = (T) new Object[arrayElements.length * 2];
        System.arraycopy(arrayElements, 0, newArrayElements, 0, arrayElements.length);
        arrayElements = (T[]) newArrayElements;
    }
}
