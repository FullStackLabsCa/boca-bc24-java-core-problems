package collections.myArrayList;

import static org.junit.Assert.assertEquals;

public class MyArrayList<T> {

    T[] currentArray;
     int index = 0;
    public MyArrayList() {
        this.currentArray = (T[]) new Object[10];
    }

    public MyArrayList(Integer size){
        this.currentArray = (T[]) new Object[size];
    }


    public void add(T element) {
        System.out.println(element);
        if(index < currentArray.length){
            currentArray[index] = element;
            index++;

        }else{
//            System.out.println(currentArray[index]);
            T[] newArray = (T[]) new Object[currentArray.length + 1];
            System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
            currentArray = newArray;
            currentArray[index] = element;
            index++;
        }

    }

    public int size() {

        return index;
    }

    public T get(int position) {
        if(position > index){
            throw new IndexOutOfBoundsException();
        }
        for(int index = 0; index <= position; index++){
            if(index  == position){
                return currentArray[position];
            }
        }
        return currentArray[position];

    }

    public T remove(int position) {
        T currentValue = null;
        if(position > index || position < 0){
            throw new IndexOutOfBoundsException();
        }else{
            for(int i=0; i< currentArray.length; i++){
                if(i==position){
                    currentValue = currentArray[i];
                    currentArray[i] = currentArray[i+1];


                }
            }
            index--;
            return currentValue;

        }


    }

    public boolean isEmpty() {
        if(index==0){
            return  true;
        }
        return false;
    }

    public void clear() {
        index = 0;
    }
}
