package collections.arrayList;

import java.util.Arrays;

public class ArrayListImplementation<T> {

    T[] underLyingArray;


    public ArrayListImplementation() {
        //Default Capacity of 10
    }

    public ArrayListImplementation(int capacity) {
        //Capacity User Desired
    }

    public void add(T element){
        //Double size if full and then add
        //Or simply add
    }

    public T get(int index){
        //Get Element and return it

        //If IndexOutOfBounds
            //Throw IndexOutOfBoundsException

        return null;
    }

    public T remove(int index){
        //Remove the element from the array

        //Shift the elements to the right of it towards left 1 position

        //Throw IndexOutOfBoundsException when occured

        return underLyingArray[index];
    }

    public int size(){
        return underLyingArray.length;
    }

    public void clear(){
        Arrays.fill(underLyingArray, null);
        // Set the size of the underlying array back to default
    }

    public boolean isEmpty(){
        boolean isEmpty = true;
        for(T element : underLyingArray){
            if(!element.equals(null)){
                return false;
            } else {
                isEmpty = true;
            }
        }

        if(isEmpty) return true;
        else return false;
    }

    public String toString(){

        return null;
    }

    public T[] resize(){
        //double the size of array when its called

        //create and array of double the length
        //copy the elements from the previous array to the new array
        //return the new array with half filled with previous info and half empty

        return null;
    }
}
