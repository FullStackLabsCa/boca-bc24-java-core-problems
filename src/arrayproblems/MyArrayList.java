import java.util.Arrays;

public class MyArrayList<T> {
    private Object[] arrayElement;
    private int size;
    public static final int INITIAL_VALUE = 10;

    @Override
    public String toString() {
        return "MyArrayList{" +
                "arrayElement=" + Arrays.toString(arrayElement) +
                ", size=" + size +
                '}';
    }

    public MyArrayList() {
        arrayElement = new Object[INITIAL_VALUE];
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

    public Object get(int index) {
        if (index < 0 || index >= arrayElement.length) {
            throw new IndexOutOfBoundsException();
        }
        return arrayElement[index];
    }

    public Object remove(int index) {

        if (index >= arrayElement.length) {
            throw new IndexOutOfBoundsException();
        }

        Object removedElement = arrayElement[index];

        for (int i = index; i < arrayElement.length - 1; i++) {
            arrayElement[i] = arrayElement[i + 1];
        }
        return removedElement;
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
    }
}
