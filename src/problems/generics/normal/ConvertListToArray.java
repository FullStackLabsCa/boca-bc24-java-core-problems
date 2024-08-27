package problems.generics.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertListToArray <T>{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Abhay");
        list.add("Dhruv");
        list.add("Mann");

//        System.out.println(Arrays.toString(listToArray(list)));

        String[] arr = listToArray(list, new String[list.size()]);
        System.out.println("list = " + list);
        System.out.println(list.getClass().getSimpleName());
        System.out.println("-----------------------------------------------");
        System.out.println("array = " + Arrays.toString(arr));
        System.out.println(arr.getClass().getSimpleName());
    }

    public static <T> T[] listToArray(List<T> list, T[] destinationArray){
       // destinationArray= (T[]) new Object[list.size()];

        for(int i=0; i<list.size(); i++){
            destinationArray[i]= list.get(i);
        }
        return  destinationArray;
    }
}
