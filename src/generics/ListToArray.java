package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {

//    public static <T> T[] listToArray(List<T> inputList){
//        return (T[]) inputList.toArray();
//    }

    public static <T> T[] listToArray(List<T> inputList, T[] resultingArray){
        //T[] resultingArray = (T[]) new Object[inputList.size()];
        int count = 0;

        if(inputList != null && !inputList.isEmpty()){
            for (T element : inputList) {
                resultingArray[count] = element;
                count++;
            }
        } else return null;

        return resultingArray;
    }

    public static void main(String[] args) {
        
        List<Integer> myList = new ArrayList<>();
        myList.add(4);
        myList.add(3);
        myList.add(2);
        
//        Object[] intArray = ListToArray.listToArray(myList);
//
//        System.out.println(Arrays.toString(intArray));

    }

}
