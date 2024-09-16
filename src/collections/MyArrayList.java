package collections;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList <T> {
    private int capacity;
    private T[] array;
    int index = 0;


    public MyArrayList() {
        this.array = (T[]) new  Object[this.capacity];
    }

    public void add(T element) {
        if(index == array.length){
            resize();
        }
        this.array[index] = element;
        index++;
        capacity++;
    }

    public T remove(int index){
        T removedElement = array[index];
        array[index] = null;
        for(int i= index; i < array.length - 1; i++){
            array[i] = array[i+1];

        }
        array = Arrays.copyOf(array,array.length-1);
        capacity--;
        return removedElement;
    }

    public T get(int index){
        if(index < 0 || index >= this.array.length){
            throw new IndexOutOfBoundsException("Requested index is out of bound !!");
        }
        return this.array[index];
    }

    public boolean isEmpty(){
        if(array.length == 0){
            return true;
        }
        return false;
    }


    public int size(){
        return this.array.length;
    }

    public void clear(){
        this.array = (T[]) new  Object[0];
    }

    public String toString (){
       return Arrays.toString(this.array);
    }

    public void resize(){
        array = Arrays.copyOf(array,array.length + 1);
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        System.out.println("Entry Size = " + list.size());
        list.add(1);
        System.out.println("list.size() = " + list.size());
        list.add(2);
        list.add(3);
        list.add(null);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        System.out.println("===list.size() = " + list.size() +"\n");
        list.add(9);
        list.add(10);
        System.out.println("===list.size() = " + list.size() +"\n");

        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        System.out.println("list.isEmpty() = " + list.isEmpty()+"\n");
        System.out.println("list.get(3) = " + list.get(0)+"\n");
        System.out.println(list.toString()+"\n");

        list.remove(4);
        System.out.println("");
        System.out.println(list.toString()+"\n");
        list.clear();
        System.out.println(list.toString()+"\n");

    }
}
