package problems;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//PROBLEM : GENERIC METHOD TO CONVERT LIST TO ARRAY
public class ListToArray {
    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        str.add("Mann");
        str.add("Ashish");
        str.add("Bhatt");

        System.out.println(Arrays.toString(listToArray(str,new String[str.size()])));

    }



    public static <T> T[] listToArray(List<T> list, T[] array){
        if(list == null){
            return null;
        }
        if (list.equals(Collections.emptyList())){
            return null;
        }
        if(array == null){
            System.out.println("Array cannot be null!!!");
        }
        int size = list.size();
        if (array.length < size) {

            array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        }
  //      System.out.println(array.getClass().getComponentType());

        for(int i = 0; i< list.size();i++){
            array[i] = list.get(i);
        }
        return array;

    }
}
