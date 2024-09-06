package advance.calculator;

import java.util.Arrays;

public class MyArrayList<T> {
    private Object[] elements;            //to store elements from internal array
    private static final int defaultCapacity=10;     //default capacity
    private int size=0;                    //no of elements in the list

    //constructor
    public MyArrayList() {
        elements=new Object[defaultCapacity];
    }

    //constructor with user-defined capacity
   public MyArrayList(int capacity){
        if(capacity<0){
            throw  new IllegalArgumentException("Capacity should not be negative ");
        }
        elements=new Object[capacity];   //initializing internal array
   }

    public void add(T element) {
     ensureCapacity();     //make sure there is enough space
        elements[size++] = element;   //place the element and increase the size
    }
    public void ensureCapacity(){
        if (size==elements.length){
            resize();   //if array is full ,increase the size
        }
    }
    private void resize() {
        int newCapacity = elements.length*2;  //double the size
        elements= Arrays.copyOf(elements, newCapacity);     //create and copy existing  element
    }
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    public T remove(int index) {
        checkIndex(index);
       T removedElement = (T) elements[index];
       int shiftElement = size -index-1; //calculate the number of element we have to shift
      if(shiftElement>0){
          System.arraycopy(elements,index+1,elements,index,shiftElement);
       }
      elements[--size]=null;
      return removedElement;
    }
    private void checkIndex(int index) {
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index is out of bound:  "+index);
        }
    }
    public int size() {
        return size;
    }
    public void clear() {
        for(int i=0 ;i<size;i++){
            elements[i]=null;
        }
        size=0;
                elements=new Object[defaultCapacity];
    }
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
    public class Main {
        public static void main(String[] args) {
            MyArrayList<String> list = new MyArrayList<>();

            // Adding elements
            list.add("Apple");
            list.add("Banana");
            list.add("Cherry");

            // Print the list
            System.out.println("List: " + list); // Output: [Apple, Banana, Cherry]

            // Get an element
            System.out.println("Element at index 1: " + list.get(1)); // Output: Banana

            // Remove an element
            list.remove(1);
            System.out.println("After removal: " + list); // Output: [Apple, Cherry]

            // Check the size
            System.out.println("Size: " + list.size()); // Output: 2

            // Clear the list
            list.clear();
            System.out.println("After clear: " + list.isEmpty()); // Output: true
        }
    }
}
