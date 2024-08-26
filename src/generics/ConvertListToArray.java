package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertListToArray {

//    public static <T> T[] listToArray(List<T> inputList){
//        return (T[]) inputList.toArray();
//    }

    public static <T> T[] listToArray2(List<T> inputList){
        T[] resultingArray = (T[]) new Object[inputList.size()];
        int count = 0;

        for(T element : inputList){
            resultingArray[count] = element;
            count++;
        }

        return resultingArray;
    }

    public static void main(String[] args) {
        
        List<Integer> myList = new ArrayList<>();
        myList.add(4);
        myList.add(3);
        myList.add(2);
        
        Object[] intArray = ConvertListToArray.listToArray2(myList);

        System.out.println(Arrays.toString(intArray));

    }

}
