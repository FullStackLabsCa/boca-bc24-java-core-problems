package problems.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(Arrays.toString(listToArray(list, new Integer[list.size()])));
    }

    public static <T> T[] listToArray(List<T> list, T[] returnArray) {
        if(list!=null && list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                returnArray[i] = list.get(i);
            } // returnArray = list.toArray(returnArray);
        }else{
            returnArray =null;

        }
        return returnArray;
    }

}
