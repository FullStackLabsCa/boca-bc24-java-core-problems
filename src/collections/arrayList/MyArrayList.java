package collections.arrayList;

import java.util.Arrays;

public class MyArrayList<T> {

    Object[] underLyingArray;
    int lastElementIndex;


    public MyArrayList() {
        //Default Capacity of 10
        underLyingArray = new Object[10];
    }

    public MyArrayList(int capacity) {
        //Capacity User Desired
        underLyingArray = new Object[capacity];
    }

    public void add(T element){
        //Double size if full
        if(lastElementIndex == underLyingArray.length - 1){
            resize();
        }
        //and add
        underLyingArray[lastElementIndex] = element;
        lastElementIndex++;
    }

    public T get(int index){
        //If IndexOutOfBounds
            //Throw IndexOutOfBoundsException
        if(index < 0 || index > lastElementIndex){
            throw new IndexOutOfBoundsException();
        } else {
            //Get Element and return it
            return (T) underLyingArray[index];
        }

    }

    public T remove(int index){
        Object[] tempArray = underLyingArray;

        //Remove the element from the array
        underLyingArray = new Object[underLyingArray.length - 1];

        //Shift the elements to the right of it towards left 1 position
        System.arraycopy(tempArray, 0, underLyingArray, 0, index);
        System.arraycopy(tempArray, index, underLyingArray, index + 1, tempArray.length - index - 1);

        lastElementIndex--;
        //Throw IndexOutOfBoundsException when occurred

        return (T) tempArray[index];
    }

    public int size(){
        if(lastElementIndex == 0)
            return 0;
        else {
            return lastElementIndex;
        }
    }

    public void clear(){
        underLyingArray = new Object[10];
        // Set the size of the underlying array back to default
        lastElementIndex = 0;
    }

    public boolean isEmpty(){
        if(lastElementIndex == 0) return true;
        else return false;
    }

    public String toString(){

        return null;
    }

    public void resize(){
        //double the size of array when its called

        //create an array of double the length
        //copy the elements from the temp array to the original array
        //return the new array with half filled with previous info and half empty
        Object[] tempArray = underLyingArray;
        underLyingArray = new Object[underLyingArray.length * 2];
        System.arraycopy(tempArray, 0, underLyingArray, 0, tempArray.length);

    }

    public static void main(String[] args) {

    }
}
